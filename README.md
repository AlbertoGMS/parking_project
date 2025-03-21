# parking_project
 
# versão do java
openjdk 21.0.6 2025-01-21 LTS
OpenJDK Runtime Environment Temurin-21.0.6+7 (build 21.0.6+7-LTS)
OpenJDK 64-Bit Server VM Temurin-21.0.6+7 (build 21.0.6+7-LTS, mixed mode, sharing

# Instalação do java
no VS Code tecle Ctrl + Shift + X ou clique em Extensions para abrir uma janela com as extensões.
faça a busca por Java e será a extensão da Oracle. Caso não tenha no computador, utilize a JDK Downloader da extensão.
Selecione a versão do openjdk compatível e confirme para iniciar a instalação (poderá levar alguns minutos dependendo da conexão).

# Sistema Operacional + IDE
Foi utilizada o Intellij IDEA 2023.2.1 (Community Edition)
Build #IC-232.9559.62, built on August 23, 2023
Runtime version: 17.0.8+7-b1000.8 amd64
VM: OpenJDK 64-Bit Server VM by JetBrains s.r.o.
Windows 10.0

# Execução do sistema
Ao executar o ParkingApplication.java o sistema irá rodar e abrir numa aba do navegador padrão com o endereço: http://localhost:8082/h2-console/
o usuário e senha estão no arquivo application.properties (como é um sistema de testes, foram mantidos os dados de login e senha para facilitar a execução).
Caso necessário mudar a porta padrão, a mesma está no arquivo application.properties (necessário executar novamente o sistema após a alteração).

# Problemas conhecidos
- No console ao realizar a validação dos campos digitados, será necessário teclar enter uma vez, para ficar em branco e na próxima linha digitar o nome correto e confirmar com enter.
O problema ocorre devido a um bug do sistema de execução do console.
- Alguns métodos não estão completos como o updateReservation() da classe ParkingClient.java, como estava utilizando pelo sistema de console (houveram algumas dificuldades com a interface gráfica) não estava conseguindo realizar o get para receber os dados sem fechar a função, para realizar as operações e utilizar em conjunto com o patch.

# API
A API está sendo levantada e funcionando com o Postman (outras funções funcionam no console também), irei subir o arquivo para facilitar os testes.



