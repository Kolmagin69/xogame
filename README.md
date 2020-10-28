# REST API for XO game
## Game endpoints

### Create player
`POST /player`

*Request*
```json
{
  "name":"yourName"
}
```
*Response*
```json
{
    "id": "9ad1d09f-5b93-4d78-950e-ad1ba95b0163",
    "name": "yourName"
}
```

### Create game
`POST /game`

*Request*
```json
{
  "sidePlayer1":"O",
  "playerId1":"5d0071fd-1584-4721-bcab-d82b065da009"
}
```
*Response*
```json
{
  "id": "714cefa7-37dd-4873-9413-533f37691d20",
    "type": "singlePlayer",
    "name": "XO",
    "player1": {
        "player": {
            "id": "5d0071fd-1584-4721-bcab-d82b065da009",
            "name": "Lol1"
        },
        "figure": "O"
    },
    "player2": {
        "player": {
            "id": null,
            "name": "AI"
        },
        "figure": "X"
    },
    "field": {
        "size": 3,
        "figures": [
            [
                null,
                null,
                null
            ],
            [
                null,
                null,
                null
            ],
            [
                null,
                null,
                null
            ]
        ]
    },
    "turn": "X",
    "winner": null
}
```

### Get game by id
`GET /game/{id}`

*Response*
```json
{
  "id": "e379002d-6fba-4899-96b2-38371f458418",
  "type": "singleplayer",
  "name": "10235",
  "xPlayer": {
    "type": "player",
    "id": "2a5ba390-c7b3-40bf-903c-8fba36913092"
  },
  "oPlayer": {
    "type": "AI"
  },
  "field": [
    [null, null, "O" ],
    [null, "X",  null],
    [null, null, "O" ]
  ],
  "turn": "O"
}
```

### Make turn
`POST /game/{id}/turn`
*Request*
```json
{
  "position": [2,2]
}
```

*Response*
```json
{
  "id": "e379002d-6fba-4899-96b2-38371f458418",
  "type": "singleplayer",
  "name": "10235",
  "xPlayer": {
    "type": "player",
    "id": "2a5ba390-c7b3-40bf-903c-8fba36913092"
  },
  "oPlayer": {
    "type": "AI"
  },
  "field": [
    [null, "X", null],
    [null, "X", null],
    ["O",  "X", "O" ]
  ],
  "turn": "O",
  "winner": "xPlayer"
}
```
