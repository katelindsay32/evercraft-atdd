# EverCraft ATDD

# The EverCraft ATDD Kata   

In this exercise, you will build out a simple web-based MMORPG called EverCraft using Acceptance Test-Driven Development.  

Original kata by 
Guy Royse [(@guyroyse)](https://twitter.com/guyroyse) and George Walters II [(@walterg2)](https://twitter.com/walterg2) here https://github.com/PuttingTheDnDInTDD/EverCraft-Kata

## Setup
Install [Chrome Webdriver](https://github.com/SeleniumHQ/selenium/wiki/ChromeDriver)


## Requirements

### Iteration 1 - Core

This iteration covers core functionality for leveling, combat, and character attributes.

#### Feature: Create a Character

> As a player, I want to create a character so that I can be distinguished from other characters

- has a name
- can get and set alignment
    - alignments are Good, Evil, and Neutral
- has an Armor Class that defaults to 10
- has 5 Hit Points by default
- Attributes are Strength, Dexterity, Constitution, Wisdom, Intelligence, Charisma
- Attributes range from 8 to 20 and default to 8
- Thirty points to spend on attributes, can be increased to a maximum of 18
- Point costs increase as attribute is raised

| Attribute  | Point Cost |
|:----------:|:----------:|
|   __8__    |    +1      |
|   __9__    |    +1      |  
|   __10__   |    +1      |  
|   __11__   |    +1      |  
|   __12__   |    +1      |  
|   __13__   |    +1      |
|   __14__   |    +2      |
|   __15__   |    +2      |
|   __16__   |    +3      |
|   __17__   |    +3      |

```gherkin
Given a new game is started
When I create a character
Then my character shows up in the game

Given a game is already started
And I created a character
When I join the game
Then my character should be ready to quest
```

#### Feature: Go on a quest

> As a player, I want to have bosses to fight to make the game interesting

Boss meta-data is retrieved from the boss service.

```json
{
  "name": "Voldemort",
  "alignment": "evil",
  "armor": 10,
  "hitPoints": 10,
  "attributes": [
      {"name": "strength", "value": 10},
      {"name": "dexterity", "value":  10},
      {"name": "constitution", "value":  10},
      {"name": "wisdom", "value":  10},
      {"name": "intelligence", "value":  10},
      {"name": "charisma", "value":  10}
      ]
}
```

#### Feature: Character Can Attack and Damage Opponents

> As a combatant I want to be able to attack other combatants so that I can survive to fight another day

- roll a 20 sided die (don't code the die)
- roll must meet or beat opponents armor class to hit
- If attack is successful, other character takes 1 point of damage when hit
- If a roll is a natural 20 then a critical hit is dealt and the damage is doubled
- when hit points are 0 or fewer, the character is dead


#### Feature: Character Ability Modifiers Modify Attributes

> As a character I want to apply my ability modifiers improve my capabilities in combat so that I can vanquish my enemy with extreme prejudice

- Attributes have modifiers according to the following table

|   Score   | Modifier |
|:---------:|:--------:|
|   __8__   |    -1    |
|   __9__   |    -1    |
|  __10__   |     0    |
|  __11__   |     0    |
|  __12__   |    +1    |
|  __13__   |    +1    |
|  __14__   |    +2    |
|  __15__   |    +2    |
|  __16__   |    +3    |
|  __17__   |    +3    |
|  __18__   |    +4    |
|  __19__   |    +4    |
|  __20__   |    +5    |

- add Strength modifier to:
    - attack roll and damage dealt
    - double Strength modifier on critical hits
    -  minimum damage is always 1 (even on a critical hit)
- add Dexterity modifier to armor class
- add Constitution modifier to hit points (always at least 1 hit point)

** Note: Group come up with something for wisdom, intelligence, and charisma. 

#### Feature: A Character can gain experience when attacking

> As a character I want to accumulate experience points (xp) when I attack my enemies so that I can earn bragging rights at the tavern

- When a successful attack occurs, the character gains 10 experience points

#### Feature: A Character Can Level

> As a character I want my experience points to increase my level and combat capabilities so that I can bring vengeance to my foes

- Level defaults to 1
- After 1000 experience points, the character gains a level
    - 0 xp -> 1st Level
    - 1000 xp -> 2nd Level
    - 2000 xp -> 3rd Level
    - etc.
- For each level:
    - hit points increase by 5 plus Con modifier
    - 1 is added to attack roll for every even level achieved

### Iteration 2 - Classes

Classes that a character can have.

#### Feature: Characters Have Classes

> As a player I want a character to have a class that customizes its capabilities so that I can play more interesting characters

##### Ideas

- changes in hit points
- changes in attack and damage
- increased critical range or damage
- bonuses/penalties versus other classes
- special abilities
- alignment limitations

##### Samples

> As a player I want to play a Fighter so that I can kick ass and take names

- attacks roll is increased by 1 for every level instead of every other level
- has 10 hit points per level instead of 5

> As a player I want to play a Rogue so that I can defeat my enemies with finesse

- does triple damage on critical hits
- ignores an opponents Dexterity modifier (if positive) to Armor Class when attacking
- adds Dexterity modifier to attacks instead of Strength
- cannot have Good alignment

> As a player I want to play a Monk so that I can enjoy being an Asian martial-arts archetype in a Medieval European setting

- has 6 hit point per level instead of 5
- does 3 points of damage instead of 1 when successfully attacking
- adds Wisdom modifier (if positive) to Armor Class in addition to Dexterity
- attack roll is increased by 1 every 2nd and 3rd level

> As a player I want to play a Paladin so that I can smite evil, write wrongs, and be a self-righteous jerk

- has 8 hit points per level instead of 5
- +2 to attack and damage when attacking Evil characters
- does triple damage when critting on an Evil character (i.e. add the +2 bonus for a regular attack, and then triple that)
- attacks roll is increased by 1 for every level instead of every other level
- can only have Good alignment

### Iteration 3 - Races

Races that a character can be.

#### Feature: Characters Have Races

> As a player I want to play a Human so that I can be boring and unoriginal

- all characters default to Human

> As a player I want a character to have races other than Human that customize its capabilities so that I can
play more interesting characters and wont be boring and unoriginal

##### Ideas

- changes in abilities
- increased critical range or damage
- bonuses/penalties versus other races
- special abilities
- alignment limitations

##### Samples

> As a player I want to play an Orc so that I can be crude, drunk, and stupid

- +2 to Strength Modifier, -1 to Intelligence, Wisdom, and Charisma Modifiers
- +2 to Armor Class due to thick hide

> As a player I want to play a Dwarf so that I can drink more than the orc

- +1 to Constitution Modifier, -1 to Charisma Modifier
- doubles Constitution Modifier when adding to hit points per level (if positive)
- +2 bonus to attack/damage when attacking orcs (Dwarves hate Orcs)

> As a player I want to play an Elf so that I can drink wine and snub my nose at the crude dwarf and orc

- +1 to Dexterity Modifier, -1 to Constitution Modifier
- does adds 1 to critical range for critical hits (20 -> 19-20, 19-20 -> 18-20)
- +2 to Armor Class when being attacked by orcs

> As a player I want to play a Halfling so that I can steal from the other drunk characters

- +1 to Dexterity Modifier, -1 to Strength Modifier
- +2 to Armor Class when being attacked by non Halfling (they are small and hard to hit)
- cannot have Evil alignment

### Iteration 4 - Weapons, Armor & Items

Items that enhance a characters capabilities.

#### Feature: Weapons

> As a character I want to be able to wield a single weapon so that I can achieve victory through superior firepower

- character can wield only one weapon

##### Ideas

- basic weapons that improve damage (dagger)
- basic weapons that improve to attacks (+1 sword)
- magic weapons with special properties (knife of ogre slaying)
- weapons that certain classes or races can or cannot wield

##### Samples

> As a character I want to be able to wield a longsword so that I can look cool

- does 5 points of damage

> As a character I want to be able to wield a +2 waraxe that so that I can *be* cool

- does 6 points of damage
- +2 to attack
- +2 to damage
- triple damage on a critical (quadruple for a Rogue)

> As an elf I want to be able to wield a elven longsword that so I can stick it to that orc with the waraxe

- does 5 points of damage
- +1 to attack and damage
- +2 to attack and damage when wielded by an elf *or* against an orc
- +5 to attack and damage when wielded by an elf *and* against orc

> As a monk I want nun chucks that work with my martial arts so that I can kick ass like Chuck Norris

- does 6 points of damage
- when used by a non-monk there is a -4 penalty to attack

#### Feature: Armor

> As a character I want to be able to don armor and shield so that I can protect myself from attack

- character can only don one shield and wear one suit of armor

##### Ideas

- basic armor that improves armor class (plate)
- magic armor that has special properties
- armor and shields that are or are not usable by certain races or classes

##### Samples

> As a character I want to the be able to wear leather armor so that I can soften attacks against me

- +2 to Armor Class

> As a fighter (or dwarf) I want to be able to wear plate armor so that I can ignore the blows of my enemies

- +8 to Armor Class
- can only be worn by fighters (of any race) and dwarves (of any class)

> As a character I want to the be able to wear magical leather armor of damage reduction so that I can soften attacks against me

- +2 to Armor Class
- -2 to all damage received

> As an elf I want to be able to wear elven chain mail so that I can fit in with all the other elves

- +5 to Armor Class
- +8 to Armor Class if worn by an elf
- +1 to attack if worn by an elf

> As a fighter I want to be able to hold a shield in my off-hand so that I can block incoming blows

- +3 to Armor Class
- -4 to attack
- -2 to attack if worn by a fighter

#### Feature: Items

> As a character I want to be able to have items that enhance my capabilities so that I can be more bad-ass

- can carry multiple items

##### Ideas
- items that improve combat with types of weapons
- items that improve stats against enemies with a certain alignment or race
- items that improve abilities

##### Samples

> As a character I want to be able to wear a ring of protection so that I can be protected from attack

  - adds +2 to armor class

> As a character I want to be able to wear a belt of giant strength so that I can be even stronger in combat

  - add +4 to Strength Score

> As a character I want to be able to wear an amulet of the heavens so that I can strike down evil with holy power

  - +1 to attack against Neutral enemies
  - +2 to attack against Evil enemies
  - double above bonuses if worn by a paladin

### Bonus Iteration - Battle Grid

Build your own features here.  Multiple characters can be on a grid-based map.  Each square on the map had terrain
that impacts the occupant or opponents attacking into it.  Characters can move and weapons have ranges.

