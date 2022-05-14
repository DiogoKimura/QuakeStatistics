# Quake Statistics

This app parse a quake 3 generated log and show the matches with its details.

This app is coded with Kotlin.

## Organization

All the parser implementation was first created in a apart project https://github.com/DiogoKimura/RawQuakeLogParser. In this project I tried different approaches to find a better solution to parse the log.

And then I scratched an app and designed a figma project to take a perspective of the app layout. The figma can be accessed here: https://www.figma.com/file/9BuLLQYUnuSsiP2U2W8bP3/Quake-Statistics?node-id=1%3A6

## App Flow

![image-20220514105522490](C:\Users\Diogo\AppData\Roaming\Typora\typora-user-images\image-20220514105522490.png)

> Download File

The file will be downloaded from a gist repo. If the file was already downloaded, the app will not show the loading screen

> Parse File

The file will be converted to a list of Strings, some routines will run a loop and start the parsing. Each line can bring the following information:

- Start game event
- End game event
- Exit game event
- Player connected
- Player information change
- Kill
- Game information

The parser implementation will give us a list of matches, and each match will contain information of kills, deaths, players, kill mode, etc.

> Show Match List

At this moment, the log file is parsed. A list of all matches will be shown.

Every element of the list will bring a summary of the match:

- Server name
- Number of players
- Total Kills

> Show Match Details

When the user click in one match, another screen will appear. In this screen we'll have two tabs that shows us two category of information.

- Players Statistics
  - Player name
  - Score
  - Kills
  - Deaths
  - Suicide
- Kills by Mode
  - Every Kill Mode with its quantities







