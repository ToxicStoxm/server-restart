[![Github All Releases](https://img.shields.io/github/downloads/toxicstoxm/server-restart/total.svg)]()
# Server-Restart

Server-Restart is a simple Bukkit plugin that allows you to schedule restarts for your Minecraft server.

## Features/Config Settings

### Restart Schedule

Set the desired time for your server to restart.

Example: Server restarts every day at 2 AM: "02:00", 2 PM: "14:00", etc.

### Restart Presets

Presets are pre-made time schedules that you can enable or disable and combine.

Example: Server restarts every eight hours: 8-h: true, every day: 1-d: true, etc.

### Timed Restart Messages

Configure a message that the plugin will display before restarting (can be disabled).

Choose when the message should be displayed.

Example: Messages displayed 10, 5, and 1 minute(s) before restarting: 10-min: true, 5-min: true, 1-min: true, etc.

### Possible Issues

- Restart times in the schedule must be at least 10 minutes apart from each other, including preset times.
- Manually set restart times will overwrite any preset schedules. For example, a manual time of "15:48" would overwrite the preset time of "15:45".
- If you encounter any other issues, please open an issue in this repository.

## Suggestions

While this plugin is primarily developed for personal use, feel free to share any cool ideas or suggestions you may have.

## Credits

C++ Restart Programm: https://github.com/hannescam
Bukkit Plugin: https://github.com/ToxicStoxm
