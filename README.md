# REST API for XO game
## Game endpoints
### Create game
`POST /game`

*Request*
```json
{
  "side": "X"
}
```
*Response*
```json
{
  "id": "e379002d-6fba-4899-96b2-38371f458418",
  "type": "singleplayer",
  "name": "10235",
  "playerX": {
    "type": "player",
    "id": "2a5ba390-c7b3-40bf-903c-8fba36913092"
  },
  "playerO": {
    "type": "AI"
  },
  "field": [
    [null, null, null],
    [null, null, null],
    [null, null, null]
  ],
  "turn": "X"
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
