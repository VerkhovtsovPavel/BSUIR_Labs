# chat

New word in chatting

## Prerequisites

You will need [Leiningen][] 2.0.0 or above installed.

[leiningen]: https://github.com/technomancy/leiningen

## Running

To start a web server for the application, run:

    lein run

## Search DSL

    (all (with-text "Hi!")(with-sender "User")(for-period 2))       ; search in all used chats
    (current (with-text "Hi!")(with-sender "User")(for-period 2))   ; search in current chat

## License

Copyright © 2016
