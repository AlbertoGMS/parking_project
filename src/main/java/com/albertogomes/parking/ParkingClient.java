package com.albertogomes.parking;

import com.albertogomes.parking.entities.ParkingSpot;
import com.albertogomes.parking.entities.Reservation;
import com.albertogomes.parking.enumeration.ParkingSpotStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

@Component
public class ParkingClient {

    private final RestTemplate restTemplate;
    Integer numberInput;
    String typeInput;
    Double pricePerHourInput;
    ParkingSpotStatus status;
    ParkingClient parkingClient;
    Scanner scanner = new Scanner(System.in);
    ParkingSpot parkingSpot, parkingSpotUpdate;
    Reservation reservation;
    LocalDateTime localDateTime = LocalDateTime.now();
    Long idParking;
    int choice = 0;
    private Double valor;

    public ParkingClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void saveParking() {

        scanner = new Scanner(System.in);

        //set number
        System.out.println("Informe o número da vaga:");
        numberInput = scanner.nextInt();

        //set type
        while (true) {
            System.out.println("Informe o tipo da vaga(COMUM ou VIP):");
            scanner.nextLine();
            typeInput = scanner.nextLine().trim().toUpperCase();

            if (typeInput.equals("COMUM") || typeInput.equals("VIP")) {
                break;
            }
        }

        //set pricePerHour
        System.out.println("Informe o preço da vaga (hora):");
        pricePerHourInput = scanner.nextDouble();

        //set status
        status = null;
        boolean validateStatus = false;

        //Loop for valid status
        while (!validateStatus) {
            System.out.println("Informe o status da vaga(DISPONIVEL, RESERVADA ou OCUPADA)");
            scanner.nextLine();
            String statusInput = scanner.nextLine().trim();

            try {

                status = ParkingSpotStatus.valueOf(statusInput.toUpperCase());
                System.out.println("Dados cadastrados com sucesso: " + "\nNúmero da vaga: " + numberInput +
                        "\nTipo da vaga: " + typeInput + "\nPreço: " + pricePerHourInput + "\nvalor de status: " + status);

                validateStatus = true;

            } catch (IllegalArgumentException e) {
                e.fillInStackTrace();
            }
        }
        parkingSpot = new ParkingSpot(null, numberInput, typeInput, pricePerHourInput, status);

        String url = "http://localhost:8082/space";
        String postResponse = restTemplate.postForObject(url, parkingSpot, String.class);
        System.out.println("Server response: " + postResponse);

    }

    public void getParkingAvailable() {
        String url = "http://localhost:8082/space/available";
        restTemplate.getForObject(url, List.class);
        System.out.println("Vagas cadastradas no sistema: \n" + restTemplate.getForObject(url, List.class));
    }

    public void saveReservation() {
        scanner = new Scanner(System.in);

        System.out.println("Informe o ID da vaga disponível:");
        numberInput = scanner.nextInt();

        reservation = new Reservation(null, Math.toIntExact(numberInput), localDateTime, null, null);
        String url = "http://localhost:8082/reservations";
        String postResponse = restTemplate.postForObject(url, reservation, String.class);
        System.out.println("Server response: " + postResponse);
    }

    public void updateReservation() throws JsonProcessingException {
        scanner = new Scanner(System.in);
        Long idReservation;
        LocalDateTime dateTimeReservation;
        Double valor;

        System.out.println("Verifique a ID da reserva a ser encerrada:");
        numberInput = scanner.nextInt();

        System.out.println("Digite a quantidade de horas utilizadas:");
        Long timeInput = scanner.nextLong();

        Long idNumberInput = numberInput.longValue();
        String idUpdateReservation = ("http://localhost:8082/reservations/" + numberInput);
        String idParkingGet = ("http://localhost:8082/space/" + numberInput);

        ParkingSpot parkingSpot1 = restTemplate.getForObject(idParkingGet, ParkingSpot.class, idNumberInput);
        Reservation reservation1 = restTemplate.getForObject(idUpdateReservation, Reservation.class, idNumberInput);
        System.out.println("Reservation URL " + reservation1);
        System.out.println("ParkingSpot URL " + parkingSpot1);

        ObjectMapper objectMapper = new ObjectMapper();

        Reservation reservationResponse = objectMapper.readValue(idUpdateReservation, Reservation.class);
        ParkingSpot parkingSpotResponse = objectMapper.readValue(idParkingGet, ParkingSpot.class);

        idReservation = reservationResponse.getParking_spot();
        dateTimeReservation = reservationResponse.getStartDate();
        valor = parkingSpotResponse.getPricePerHour();

        System.out.println("Valores recebidos: " + idReservation + " " + dateTimeReservation + " " + valor);

        System.out.println("Valor de idReservation" + idReservation + "vlor de time" + dateTimeReservation);

        Double total = valor * timeInput;
        reservation = new Reservation(idReservation, dateTimeReservation, dateTimeReservation, total);
        String url = ("http://localhost:8082/reservations" + numberInput);
        String postResponse = restTemplate.patchForObject(url, reservation, String.class);
        System.out.println("Server response: " + postResponse);

    }


    public void menuParking() {
        while (true) {
            // Display menu
            System.out.println("\n----- Menu -----");
            System.out.println("1. Cria vaga");
            System.out.println("2. Lista as vagas disponíveis");
            System.out.println("3. Salva a reserva");
            System.out.println("4. Encerra a reserva");
            System.out.println("5. Finaliza o programa");
            System.out.print("Escolha uma opção (1-5): ");

            // Get user choice
            try {
                choice = scanner.nextInt();

                // Handle user choice
                switch (choice) {
                    case 1 -> saveParking();
                    case 2 -> getParkingAvailable();
                    case 3 -> saveReservation();
                    case 4 -> updateReservation();
                    case 5 -> {
                        System.out.println("Programa finalizado com sucesso");
                        scanner.close();
                        return;
                    }
                    default -> System.out.println("Opção inválida, por favor escolha uma opção da lista acima.");
                }
            } catch (Exception e) {
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }


}
