# rpg-project
A simple tabletop roleplaying manager made in JEE with Spring and Velocity.

## Features: 
- Simple user functionality (login/register)
- Party management up to 5 users (4 players & 1 GM)
- Character sheet creation and edition
- Party creation and edition
- Spells API (see openAPI file):
  - by type
  - by level
  - by name 

## Requirements
- JDK 11
- MySQL server
- Tomcat 9.0

## Running
- Import as maven project in IntelliJ
- Modify `db.properties` as necessary
- Run `project_dump.sql` file to populate database
- Start server
---
## API documentation

Also available as a swagger `.yaml` file in the `resources` folder.

### Base URL : `localhost:8080/project_web_war_exploded/api`

**GET** `/spells` 

> Gets all the spells 
> 
> Returns a map with spell id and name
> ```
> {
>     "id": "spell_name",
>     "id": "spell_name",
>     ...
> }
> ```

**GET** `/spells/{name}=name` 

> Gets all the spells which names' contain the parameter > given in `{name}`
> 
> Returns a list of spells
> ```
> {
>     [
>         {
>             name: string
>             type: string
>             level: integer
>             description: string
>         },
>         {
>             name: string
>             type: string
>             level: integer
>             description: string
>         },
>         ...
>     ]
> }
> ```

**GET** `/spells/{type}=type` 

> Gets all the spells which are of type: `{type}`
>
> The types present in the provided SQL dump are : 
> - Abjuration
> - Conjuration
> - Divination
> - Enchantment
> - Evocation
> - Illusion
> - Necromancy
> - Transmutation
> - Universal
> 
> Returns a list of spells
> ```
> {
>     [
>         {
>             name: string
>             type: string
>             level: integer
>             description: string
>         },
>         {
>             name: string
>             type: string
>             level: integer
>             description: string
>         },
>         ...
>     ]
> }
> ```

**GET** `/spells/{level}=level` 

> Gets all the spells which are of level: `{level}`
> 
> Returns a list of spells
> ```
> {
>     [
>         {
>             name: string
>             type: string
>             level: integer
>             description: string
>         },
>         {
>             name: string
>             type: string
>             level: integer
>             description: string
>         },
>         ...
>     ]
> }
> ```

### These three routes can be refined with the suffix route `/{limit}=limit`

This will return a random list of size `{limit}` corresponding to the previous parameter.

<u>Example :</u>
`GET spells/2=level/2=limit` returns 2 random spells of level 2 
```
[
    {
        "name":"Twilight Haze",
        "type":"Illusion",
        "level":2,
        "description":"Illusory fog obscures vision."
    },
    {
        "name":"Share Memory",
        "type":"Divination",
        "level":2,
        "description":"Share one memory with the target."
    }
]
```


---
Made by:
- [ARBACHE Rémi](https://github.com/RemiArbache) 
- [DALMAS Eugénie](https://github.com/Proton013)