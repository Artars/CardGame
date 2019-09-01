# CardGame
A card game for a Object Oriented Programming project
Based on the idea of making a TCG with the original card deck.
It's a Netbeans Project, written in Java.


# Descrição em Português
Deck King X foi um projeto desenvolvido para a matéria de Programação Orientada a Objeto, ministrada pelo professor João Batista, na USP. Presente nesse repositório é um projeto Netbeans, escrito em java.
A ideia é fazer um jogo de cartas com jogabilidade similar à de TGC (Trading card games), como Hearthstone, Magic e Yu-gi-oh. Mas para isso foram usadas as cartas padrões de baralho.

Aqui serão apresentadas as regras de jogo.

## Regras:
### Objetivo
Eliminar o inimigo, atacando-o diretamente com suas cartas de atacante até acabar com todos os seus pontos de vida.

### Disposição do tabuleiro
O tabuleiro é separado em 3 diferentes partes.
#### 1. Mão
Cada jogador pode possuir até 5 cartas em sua mão. Na sua rodada, o jogador pode colocar quantas cartas quiser no seu campo ou descartar.
#### 2. Descarte
O descarte é utilizado pelos dois jogadores. Ele acumula as últimas 5 cartas de foram descartadas. Se o descarte estiver cheio, todas as cartas são movidas pra um descarte definitivo, onde não afetarão mais o jogo.
#### 3. Mesa
Cada jogador possui sua própria mesa, onde pode posicionar 5 cartas. É nele onde são colocadas todas as cartas responsáveis pela dinâmica de jogo.

### Cartas
Cada carta possui "número" e um naipe. O seu tipo, que depende do Naipe da carta, afeta a função da carta em jogo. O número da carta afetará os seus atributos.
#### 1. Atacante
São as cartas dos naipes de ouros e espadas. Com sua ação, são capazes de atacar o espaço no tabuleiro à frente, podendo ferir outra carta ou o inimigo diretamente (se não houver uma carta na frente). A quantidade de ataque da carta é dependente do "número" da carta. Ela possui um quantidade de vida, que é dependente do "número" da carta. Se acabar todos os seus pontos de vida, a carta é descartada.
#### 2. Defensora
São as cartas do naipe de paus. Com sua ação, são capazes de trocar de posição com outras cartas da mesa. Sua quantidade de vida depende do seu "número". Possui mais vida do que suas contraparte atacante. Se acabar todos os seus pontos de vida, a carta é descartada.
#### 3. Curandeira
São as cartas do naipe de copas. A sua ação surte efeito imediatamente ao ser colocada. Ela pode ser colocada em outras cartas ou em espaços vazios da mesa. Sua ação é curar uma carta (se posicionada em uma carta) ou o jogador (se posicionada em espaço vazio). Após seu uso, a carta é descartada.

### Fluxo de jogo
Os jogadores jogam alternadamente.
Em seu turno, um jogador pode:

#### Sacar cartas
O jogador deverá sacar uma quantidade de cartas proporcional ao número de cartas que ele possui na mesa. Ele nunca pode passar de 5 cartas na mão.
* 0-2: o jogador pegará 2 cartas
* 2-4: o jogador pegará 1 carta
* 5: o jogador não poderá pegar mais cartas

#### Colocar cartas
O jogador pode colocar cartas da sua mão na mesa. Ele pode colocar em qualquer um dos 5 espaços da mesa que estejam livres. Não há limite de quantas cartas podem ser colocadas por turno. O jogador pode também descartar cartas, acrescentando no final do monte de descartes.

#### Utilizar ações
O jogador pode utilizar as ações das suas cartas na mesa para atacar ou trocar de posição (Atacante e Defensor). Cada carta só pode ter sua ação utilizada uma vez por turno.
**Observação: não é permitido ataque na primeira rodada do jogo.**

### Multiplicadores
Durante o jogo, qualquer carta pode ter seus atributos multiplicados. Os atributos multiplicados podem ser “Vida”, “Ataque” e “Cura”. Esse efeito ocorre sempre ocorre quando há mais de uma carta do mesmo “número” no “campo” de um jogador. Uma carta está no “campo” de um jogador se ela estiver na sua mesa ou no descarte. Não se considera um campo a mão dos jogadores ou a mesa do inimigo.
Os multiplicadores são os seguintes:
* 2x: duas cartas no “campo” 
* 3x: três cartas no “campo”
* 4x: quatro cartas no “campo”

### Balanceamento
Esses são os valores de balanceamento utilizados no jogo original. O balanceamento original valoriza cartas especiais, como “Ás”, “Valete”, “Dama” e “Rei”. As outras cartas possuem valores de vida e cura proporcionais ao seu número para os defensores. Para os atacantes, a vida é inversamente proporcional ao número e a força é diretamente proporcional ao número.
Esses valores podem ser alterados se desejado. Eles estão presentes nas classes “[Atacante.java](CardGame/src/Cartas/Atacante.java)”, “[Defensor.java](CardGame/src/Cartas/Defensor.java)” e “[Curandeiro.java](CardGame/src/Cartas/Curandeiro.java)”.

#### Atacante:
* Ás - Vida: 1, Ataque: 11
* Valete - Vida: 8, Ataque: 8
* Dama - Vida: 12, Ataque: 8
* Rei - Vida: 8, Ataque: 12
* Números (x=2~10) - Vida: 12 - x, Ataque: x

#### Defensor:
* Ás - Vida: 22
* Valete - Vida: 16
* Dama - Vida: 16
* Rei - Vida: 16
* Números (x=2~10) - Vida: 2x

#### Curandeiro
* Ás - Cura: 22
* Valete - Cura: 16
* Dama - Cura: 16
* Rei - Cura: 16
* Números (x=2~10) - Cura: 2x


