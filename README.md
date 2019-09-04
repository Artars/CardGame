# CardGame
A card game for a Object Oriented Programming project
Based on the idea of making a TCG with the original card deck.
It's a Netbeans Project, written in Java.

# English description
Deck King X was a project developed for the Object Oriented Programing class. This class had João Batista as a teacher, from USP (University of São Paulo).
The concept of the project was a TCG (Trading card games), similar to Hearthstone, Magic and Yu-gi-oh. However, it would use the standard deck cards.

## Rules:
### Objective
Defeat the enemy by attacking him directly using your attack cards. You must deplete all of the opponent's life points.

### The game table
The game table is made of three different spaces.
#### 1. Hand
Each player can have a maximum of 5 cards in hand. At the beginning of each turn, the player can draw cards. In its turn, the player can place as many cards in it's board or discard.
#### 2. Discard
The discard is shared by both players. It keeps the last 5 discarded cartas. If the discard is full and there's a new card discarded, all of the cards are removed from it and new cards can be placed. The removed cards are then shuffled and placed again with the rest of the deck.
#### 3. Board
Each player has its own board where they can place a maximum of 5 cards. These cards can realize actions, such as attack and defend.

### Cards
Each card has a "number" and a suit. The suit is used to decide the use of the card in game. The number of the card affects its attributes.
#### 1. Attacker
Are the cards of the suits 'Diamonds' and 'Spades'. Using its action, the attacker can attack the slot right in front of it in the board. If it hit another card, you damage that card. If you hit an empty slot, the enemy is hurt. This card has a given amount of "Health". If all health is depleted, this card should be discarded. The damage dealt by each card and it's "Health" are related with the "number" of the card.
#### 2. Defenders
Are the cards of the suit 'Clubs'. With its action the defenders can switch position with other cards in the board. This card has a given amount of "Health". If all health is depleted, this card should be discarded. The amount of "Health" are related with the "number" of the card.
#### 3. Healer
Are the cards of the suit 'Hearts'. This card can heal others and is used when placed. If placed in another card on board, it will cure that card. If placed on an empty slot, it will heal the player that placed the card. When used, this card should be discarded.

### Game Flow
The players play alternately, each one in it's turn.
Every turn the player can:

#### Draw cards
At the beginning of the turn, the player **must** draw cards from the deck to it's hand. The amount of cards the player draws is inversely proportional to the amount of cards the player has on it's board. The player cannot have more than 5 cards on it's hand.
* 0-2 cards on the board: the player draw 2 cards
* 3-4 cards on the board: the player draw 1 card
* 5 cards on the board: the player don't draw anymore cards

#### Place cards
The player can place cards from hand to the board. The cards can be placed in any free slots in the board. There's no limit for the amount of cards placed in one turn. The player can also discard carts, placing then at the end of the discard space.

#### Using actions
In it's turn, the player can use the actions of the cards in the board. It can be used to attack or swap position. Each card can act only once per turn.
**It's not allowed to attack in the first turn of each player.**

### Multiplier
During the match, any card can have its attributes multiplied. The attributes can be either "Health", "Attack" or "Healing". The cards will have its attributes multiplied every time there is more than one card of the same "number" in the player's 'field'. A field it's the combination of the player board and the discard. Player's hands and the enemy board do not affect multipliers.
The multipliers are the following
Os multiplicadores são os seguintes:
* 2x: two cards in the player's field
* 3x: three cards in the player's field
* 4x: four cards in the player's field

### Balancing
In this section you can find the values used for the attributes of the cards. This balancing treat some cards as special cards, like "Ace", "Jack", "Queen" and "King". The defender and healer have its attributes proportional to the "number" of the card. The attackers have it's attack attribute proportional to the "number" and the "Health" as a inverse relation.
This values can be seen and altered in the code if necessary. They are found at the classes “[Atacante.java](CardGame/src/Cartas/Atacante.java)”, “[Defensor.java](CardGame/src/Cartas/Defensor.java)” and “[Curandeiro.java](CardGame/src/Cartas/Curandeiro.java)”.

#### Attacker:
* Ace - Health: 1, Attack: 11
* Jack - Health: 8, Attack: 8
* Queen - Health: 12, Attack: 8
* King - Health: 8, Attack: 12
* Numbers (x=2~10) - Health: 12 - x, Attack: x

#### Defender:
* Ace - Health: 22
* Jack - Health: 16
* Queen - Health: 16
* King - Health: 16
* Numbers (x=2~10) - Health: 2x

#### Healer
* Ace - Heal: 22
* Jack - Heal: 16
* Queen - Heal: 16
* King - Heal: 16
* Numbers (x=2~10) - Heal: 2x

## Multiplayer:
This game has local network multiplayer. If you want to play it online, the host most open the Port 1234 (or other desired port) using *port forwarding*. The client player must type the external IP from the host and then connect.

---

# Descrição em Português
Deck King X foi um projeto desenvolvido para a matéria de Programação Orientada a Objeto, ministrada pelo professor João Batista, na USP. Presente nesse repositório é um projeto Netbeans, escrito em java.
A ideia é fazer um jogo de cartas com jogabilidade similar à de TCG (Trading card games), como Hearthstone, Magic e Yu-gi-oh. Mas para isso foram usadas as cartas padrões de baralho.

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

## Multiplayer:
O jogo possui multiplayer em LAN. Se desejar jogar online, o jogador que será o Host deverá abrir o Port 1234 utilizando *port forwarding*. O outro jogador colocará o IP dado e então conectar. 


