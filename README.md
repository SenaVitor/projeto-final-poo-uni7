# projeto-final-poo-uni7
a project for purchasing airline tickets using java and the OOP concepts acquired throughout the course

Enunciado:
Desenvolva um sistema para aquisição de passagens aéreas. Seu sistema deve executar num totem, 
onde existe apenas um teclado como interface com o usuário. Na cia aérea é permitido que um 
cliente adquira uma passagem entre quaisquer uma das cidades como origem ou destino: Fortaleza 
(FOR), São Paulo (CGH), Salvador (SSA), Brasília (BSB) e Manaus (MAO). Seu sistema apresenta um 
menu com as seguintes opções ao usuário:
1 - 'Comprar uma passagem', onde o usuário digita os aeroportos de origem e destino e o totem 
apresenta o valor da passagem e pede a confirmação do usuário. Caso o usuário confirme, o sistema 
pede o nome e CPF do cliente, a fim de emitir uma reserva para aquele voo, que inclui também o 
número de um assento aleatório.
2 - 'Alterar o assento', onde o usuário pode entrar com o código de uma reserva já feita e escolher 
algum dos assentos livres no seu voo. Caso a alteração seja feita com sucesso, o sistema altera a 
reserva existente e confirma para o usuário.
3 - 'Alterar titularidade de uma reserva', onde o usuário pode entrar com o código de uma reserva já 
feita e altera o nome e CPF da pessoa que irá viajar.
4 - 'Cancelar uma reserva', onde o usuário pode cancelar uma reserva já feita e ter seu reembolso.
5 - 'Imprimir lista de passageiros', onde o usuário pode imprimir a lista de passageiros que irão viajar 
entre uma cidade e outra. O sistema pede uma senha ao usuário, que se digitada corretamente, faz 
com que o sistema pergunte o o nome do aeroporto de origem e destino. A lista deve ser impressa 
em tela, mostrando os passageiros na ordem com que os assentos estão distribuídos no avião, 
incluindo nome, CPF e valor pago na reserva. No final da lista, deve ser impresso ainda o valor total 
das vendas de passagem daquele voo.
Utilize todos os conceitos de OO vistos na disciplina, desde que aplicáveis.
Regras:
- Seu sistema deve manter prints apenas na classe Main.
- Todas as reservas feitas são para o dia atual, não sendo necessário perguntar sobre datas ao cliente.
- Os aviões utilizados pela cia aérea tem 220 assentos, dos quais os 6 primeiros assentos possuem 
valor adicional de 50 reais. Assim, quando uma passagem é comprada e sua reserva emitida, o sistema 
só irá selecionar algum dos 6 assentos apenas caso os demais estejam ocupados. Se apenas as 6 
estiverem livres no momento da compra da passagem, então nenhum custo adicional será repassado 
ao cliente. Por sua vez, caso o cliente deseje alterar seu assento e venha a selecionar um dos 6, deverá 
ser cobrado a taxa de 50 reais, caso o cliente confirme.
- Não é necessário tratar da 'carteira' do usuário. Se o usuário concordar em pagar um determinado 
valor, o sistema apenas assume que o cliente pagou e prossegue. 
- O preço de uma passagem é diretamente proporcional à quantidade de passagens já vendidas para 
o voo. Dado que o número de passageiros do voo é igual a N, o preço é igual a 100 + 5 ^ log10(N).