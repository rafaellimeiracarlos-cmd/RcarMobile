# RCarPremium Mobile - versão sem API do Visual Studio

Esta versão remove a dependência do projeto da API/Visual Studio.

## Alterações

- Removida a dependência do Retrofit e do Gson do `app/build.gradle.kts`.
- Removidas as permissões de Internet e estado da rede do `AndroidManifest.xml`.
- Removidos `networkSecurityConfig` e `usesCleartextTraffic`.
- O cadastro agora salva o usuário localmente usando `SharedPreferences`.
- O login agora aceita usuários cadastrados localmente.
- O usuário de teste continua funcionando:
  - E-mail: `teste@rcar.com`
  - Senha: `1234`
- Não existe mais referência ao endereço `10.0.2.2:5133` nesta versão.

## Observação

O armazenamento local é adequado para um protótipo/offline. Para um aplicativo real com vários dispositivos e dados sincronizados, seria necessário um servidor/backend ou outro serviço de dados.
