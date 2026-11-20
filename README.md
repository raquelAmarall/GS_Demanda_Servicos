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

## OBS
### Decisão de modelagem — DadosTerritorio e ConfiguracaoSistema

A classe `DadosTerritorio` é responsável por armazenar as informações territoriais e os limiares de atenção, alerta e criticidade, garantindo a validação dos valores por meio de métodos específicos.

A classe `ConfiguracaoSistema` será responsável por centralizar o gerenciamento dessas configurações, utilizando o padrão Singleton para disponibilizar uma instância única durante a execução do sistema.

Essa divisão permite manter o encapsulamento e evitar a duplicação de informações, respeitando as responsabilidades de cada classe.
