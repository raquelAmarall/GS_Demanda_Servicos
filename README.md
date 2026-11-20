# Territory Predictive Insights
## Monitoramento Preditivo da Demanda por Serviços de Saúde

### 1. Problema identificado
A variação na procura por serviços públicos de saúde pode dificultar o planejamento e a distribuição adequada de recursos. Em determinados períodos, o aumento da demanda pode provocar sobrecarga nas Unidades Básicas de Saúde (UBS), prejudicando a capacidade de atendimento à população.

### 2. Objetivo do sistema
Desenvolver uma plataforma de monitoramento territorial capaz de acompanhar dados relacionados à demanda por serviços públicos de saúde, identificar tendências de crescimento e gerar previsões para os próximos períodos.

A partir dessas informações, o sistema emitirá alertas que auxiliem na identificação de possíveis períodos de sobrecarga, permitindo que os responsáveis organizem os recursos preventivamente.

### 3. Território monitorado
Uma região da cidade, considerando as Unidades Básicas de Saúde presentes nesse território.

### 4. Público beneficiado
Moradores da região que dependem dos serviços públicos de saúde.

### 5. Decisão apoiada
Apoiar o planejamento e a distribuição preventiva de recursos, contribuindo para que as UBS estejam mais preparadas para possíveis aumentos na procura por atendimentos.

### 6. Dados monitorados
O sistema trabalhará com três categorias de demanda:
- Consultas médicas;
- Atendimentos de vacinação;
- Exames.

As medições serão representadas por quantidades agregadas, sem armazenamento de dados pessoais dos pacientes.

### 7. Sensores simulados
- SensorConsultas;
- SensorVacinacao;
- SensorExames.

Cada implementação será responsável por realizar leituras, verificar seu funcionamento e informar seu tipo.

### 8. Solução proposta
O sistema utilizará dados simulados para registrar medições, armazenar um histórico em banco de dados, realizar previsões por diferentes estratégias e emitir alertas classificados em ATENÇÃO, ALERTA e CRÍTICO.

Os resultados servirão como apoio à tomada de decisão dos responsáveis pelas unidades de saúde, sem substituir a avaliação humana.

## 9. Arquitetura e decisões técnicas

O sistema foi desenvolvido em Java, utilizando Programação Orientada a Objetos e organização em pacotes para separar modelos, serviços, persistência, conexão com o banco de dados e execução do menu principal.

Foi aplicado o padrão criacional **Factory**, por meio da classe `FabricaPrevisao`, responsável por centralizar a criação das estratégias de previsão. Essa escolha evita que a lógica de instanciação fique espalhada pelo sistema e facilita a inclusão de novas estratégias.

Também foi utilizado o padrão **Singleton** na classe `ConexaoBD`, garantindo uma instância compartilhada para gerenciar a conexão com o banco de dados.

## 10. Banco de dados

O projeto utiliza Oracle Database com conexão JDBC.

São utilizadas duas tabelas:

- `MEDICAO`: armazena o tipo do sensor, o valor coletado e a data e hora da medição.
- `ALERTA`: armazena o tipo do sensor, o valor previsto, o limiar ultrapassado, o nível do alerta e a data e hora.

As operações de persistência são realizadas pelas classes `MedicaoDAO` e `AlertaDAO`.

Para executar o projeto, é necessário configurar as credenciais de acesso ao Oracle da FIAP na classe `ConexaoBD`, disponibilizar o driver JDBC.


## 11. Testes automatizados

Foram implementados cinco testes automatizados utilizando JUnit 5 e Mockito.

Os testes verificam:

- Rejeição de população negativa.
- Rejeição de limiares inválidos.
- Cálculo da média móvel.
- Cálculo da previsão por comparação de períodos.
- Emissão de alerta utilizando DAOs simulados com Mockito.

Os testes seguem a estrutura AAA (Arrange, Act, Assert), incluindo validação de exceções personalizadas com `assertThrows`.

## 12. Tecnologias utilizadas

- Java 21
- Oracle Database
- JDBC
- JUnit 5
- Mockito
- Maven
- Eclipse IDE
- Git e GitHub

## 13. Uso de Inteligência Artificial

A inteligência artificial foi utilizada como ferramenta de apoio durante o desenvolvimento, principalmente para esclarecer conceitos de programação, compreender padrões de projeto, auxiliar na interpretação de erros e apoiar a elaboração de testes.

O código foi desenvolvido e revisado pela equipe, com análise das sugestões, execução do sistema e realização de testes para verificar o funcionamento das implementações. A IA foi utilizada como apoio ao aprendizado, não como substituta da compreensão do código.

## 14. Integrantes
- Eduardo Bacelar Rudner RM 564925
- Giovana Dias Valentini RM  562390
- Raquel Amaral de Oliveira RM 566491

## 15. Reflexão crítica

O sistema deve evitar emitir previsões quando não existem dados suficientes para sustentar uma análise minimamente confiável. Uma quantidade pequena de medições pode não representar adequadamente o comportamento da demanda, tornando inadequado apresentar uma previsão como se fosse uma informação segura. Por isso, a disponibilidade e a qualidade dos dados são fundamentais.

Além disso, os dados monitorados não representam toda a realidade de um território. As medições são simuladas e agregadas por categoria de serviço, não contemplando diferenças entre unidades de saúde, características individuais dos pacientes, sazonalidades específicas ou acontecimentos que possam alterar repentinamente a procura por atendimento.

Por esse motivo, os alertas devem funcionar como instrumentos de apoio à decisão humana, e não como decisões automáticas. Uma previsão indica uma possível tendência de aumento da demanda, mas não garante que ela ocorrerá. Os profissionais responsáveis precisam considerar o contexto local, avaliar as condições das unidades e interpretar os resultados antes de decidir como distribuir os recursos.

Assim, o sistema contribui para o planejamento preventivo, mas suas informações devem ser analisadas com senso crítico e não substituem a experiência dos profissionais de saúde.
