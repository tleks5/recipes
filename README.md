WYMAGANIA:
NBD project

Total is 80 points

You can do it alone or in a team of max. 2 people

The main idea is that it's supposed to be functional - useful, interesting, flashy, complicated - as long as it doesn't look like an barely functional university project made just to pass (we’ve all been there).

If the project involves two people, it should be more advanced. For instance, if it's a project involving some website, it should have a proper frontend. If it's a CLI tool, or some other programming library/wrapper, it should have a proper documentation, or help functions, proper error codes. Basically, use the fact that you have twice as many people working and make the project better and more polished. You can make it good enough to publish on your github and showcase in your CV.

What you hand in should consist of:

- All the code

- All data files

- All library lists (package.jsons, requirements.txt, pyproject.toml, etc, etc.)

Remember that regardless of what you're making, I'd like to see a simple README file with what the project is, what it consists of and what it does. It should also contain instructions on how to run it and/or connect to it if you self-hosted it yourself. I'd also like to see a demo - it can be done in person, or recorded as a video. It’s ideal if you show both the technical side, and the actual working project. Take this opportunity to talk about some design choices, maybe some tricky issues you had to solve, or maybe features you had planned but decided to skip. When making the demo, make sure to include enough data, and try to make it actually real – so no “abcdabcd” or “test123”, but proper entries.

The database system is entirely up to you, you can mix and match them as you like. You can also add a relational database for parts of it, as long as there’s at least one non-relational database used.

Programming language and other technologies are also up to you, but I strongly recommend using docker. At worst, it’ll help you run some linux-specific utilities, and at best it’ll make configuring all dependencies a breeze.

The requirements are intentionally left vague because I want you to be creative. However, some ideas for your consideration:

1. A chat allowing two (or more) users to talk, send images and do some activities together (for instance, there can be a campfire on the side of the screen and users can click on it and make it burn brighter for all users). Messages could be stored for a set amount of time, or we can store only the last 100 messages. Sharing images and a few different activities are necessary – just sending text isn’t enough. Alternatively, you can allow users to make their own servers, channels and groups, or implement a friends system. Suggested database: redis

2. Cooking website that allows users to submit their own recipes, while also including a search functionality – you input what ingredients you have in your fridge, and it suggests what you can prepare using them, if nothing is found it tries finding something that requires least shopping to make. Bonus points if it somehow handles replacing ingredients (such as sugar=>honey, normal flour=>corn flour…)

3. A system for music or video game recommendations – you can take inspiration from https://www.music-map.com/. You can include various systems such as ranking the recommendations, taking user feedback into account (two games sharing the same genre can appeal to vastly different audiences), tag systems…; this is probably best done in neo4j

4. A simple, text-based (or mostly static image-based) multiplayer game, similar to OGame or Empire Universe. Allow the users to register and login, save their progress in the database, allow them some basic interaction (more than a chat). If you’re feeling particularly inspired, you can even go for something like DarkOrbit and include a real-time section, for instance for combat. Depending on what you’re going for, this is probably one of the more difficult projects.
