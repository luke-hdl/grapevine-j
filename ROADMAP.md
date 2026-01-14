# Pre-UI Work
## Loading and Saving Games
- Correctly persist *all* canonical sheet fields. 
- Support loading fields from custom menus.
- Partial load (importing an Exchange file - Grapevine J uses the Exchange as its basic file format for games as well, but it should be possible to load an Exchange file 'into' a game as well.)

## Printing Sheets
- Print using the existing Grapevine template files.

# UI Work
## Planning work
- Pick a front-end. (Grails+Electron? Swing?)
  - I would like to continue supporting headless operation for use with scripting, etc.
- Work out some basic mock-ups, maybe get feedback from Grapevine Enjoyers

## Viewing a game, basics
- Load a game from a UI file-picker
- Look at the characters and players of a game from that UI. 
  - Although we load and save rumors, plot, etc. in order to persist that information back into a GEX 
  for interoperability purposes, this is not at all a high priority for Grapevine J, 
  as I am not aware of any current Grapevine-backed games using these features. 
- Print sheets from a UI file-picker, including picking a template

## Editing a game, basics
- Save game state from a UI file-picker
- Edit players
- Edit characters

