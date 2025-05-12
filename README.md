[![Discord Shield](https://discordapp.com/api/guilds/452233740408717313/widget.png?style=shield)](https://discord.gg/KTgshSbEUU)
[![BSD 3-Clause License](https://img.shields.io/badge/license-BSD%203--Clause-blue.svg)](https://opensource.org/license/bsd-3-clause)
[![GitHub release](https://img.shields.io/github/release/Ixirsii/Clash4J.svg?style=flat-square)](https://github.com/Ixirsii/Clash4J/releases/latest)
[![Codecov](https://img.shields.io/codecov/c/github/Ixirsii/Clash4J?logo=codecov&style=flat-square)](https://codecov.io/gh/Ixirsii/Clash4J)
[![GitHub Workflow Status](https://img.shields.io/github/actions/workflow/status/Ixirsii/Clash4J/build.yml?branch=main&logo=github&style=flat-square)](https://github.com/Ixirsii/Clash4J/actions?workflow=build)

# Clash4J
Clash of Clans API for Java

# Quick start

## Installation

### Gradle

```kotlin
dependencies {
    implementation("tech.ixirsii:clash4j:1.0.0")
}
```

## Usage

### Authenticating

1. Go to the [Clash of Clans developer portal](https://developer.clashofclans.com) and create
   an account.
2. Generate a new token.
3. Pass the token to the `ClashAPI` constructor.

```java
final String token = System.getenv("API_TOKEN");
final ClashAPI clashAPI = new ClashAPI(token);
```

### Making requests

Most of the APIs take a tag parameter. This is the player, clan, or clan war
tag. In game these are prefixed with a '#' but you should omit this when
passing the tag to the API.

#### Synchronous

`Clash4J` uses [Project Reactor](https://projectreactor.io/) to provide an
asynchronous API. However, if you want to make synchronous requests, you can
simply call the `block()` method on the `Mono` returned by the request.

```java
final Clan clan = clashAPI.clan(clanTag).block();
```

#### Asynchronous

```java
clashAPI.clan(clanTag)
    .subscribe(clan -> {
        // Do stuff with clan
    });
```

# Contributing

## Running integration tests

Set the `API_KEY` environment variable to your API token.
