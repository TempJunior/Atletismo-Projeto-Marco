# Atletismo-Projeto-Marco
Projeto proposto na minha disciplina de técnicas de desenvolvimento de algoritmos.

# Projeto de Gerenciamento de Atletas

**Professor:** Marco Antonio Sanches  
**Disciplina:** Técnicas de Desenvolvimento de Algoritmos

## Contexto
Você foi contratado para trabalhar em um projeto que consiste no desenvolvimento de um sistema para gerenciar informações de atletas do gênero masculino de países da América do Norte que participam de uma competição de 100 metros rasos. O sistema deve permitir o cadastro de atletas com nome, país, idade e melhor tempo, além de realizar operações como exibição de todos os atletas, cálculo do tempo médio por país, e identificação do atleta mais rápido.

## Menu de Opções:
- Cadastro de atleta (nome, país, idade e melhor tempo nos 100 metros rasos)
- Exibição de todos os atletas cadastrados
- Exibição do atleta mais rápido e do país vencedor (com base no menor tempo médio)
- Sair do programa

## Requisitos:
- **Nome:** deve ser do tipo literal (String em Java)
- **Países permitidos:** MEX, USA ou CAN
- **Idade:** entre 18 e 35 anos
- **Gênero:** M
- **Melhor tempo em segundos:** número decimal positivo (duas casas decimais)

## Validações:
- **País:** deve estar entre os permitidos
- **Idade:** deve estar no intervalo especificado
- **Limite de atletas:** não deve permitir mais que 100 atletas cadastrados

## Mensagens de Erro e Sucesso:
- **Cadastro de atleta:**
  - Sucesso: "Atleta cadastrado com sucesso!"
  - Erro: "Erro: limite de atletas atingido!" ou "Erro: Dados inválidos, tente novamente."
- **Exibição de atletas cadastrados:**
  - Nenhum atleta cadastrado: "Nenhum atleta registrado ainda."

## Implementação
O projeto deve ser implementado em Java, com a estrutura adequada para gerenciamento e validação dos dados dos atletas.

## Autor
Joselito Junior de Oliveira Barreto

---
