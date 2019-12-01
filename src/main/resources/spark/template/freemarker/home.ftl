<!DOCTYPE html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
  <meta http-equiv="refresh" content="10">
  <title>Web Checkers | ${title}</title>
  <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>

<body>
<div class="page">

  <h1>Web Checkers | ${title}</h1>

  <!-- Provide a navigation bar -->
  <#include "nav-bar.ftl" />

  <div class="body">

    <!-- Provide a message to the user, if supplied. -->
    <#include "message.ftl" />

  <#if currentUser??>
      <div style="width: 100%;">
        <h2>Players Online</h2>

        <#if playerList?size == 1>
            <p>There are no other players online.</p>
        </#if>

        <ul>
        <#list playerList as item>
            <#if item != currentUser>
                <li>${item.getName()}
                    <form id="startGame" action="./" method="POST">
                    <button name="button" type="submit" value=${item.getName()}>Start Game</button>
                    </form>
                </li>
            </#if>
        </#list>
        </ul>
      </div>

      <div style="width: 100%;">
          <h2>Spectate Active Game</h2>
          <#if gameList?size == 0>
              <p>There are no active games.</p>
          </#if>

          <ul>
              <#list gameList as game>
                      <li>${game.getName()}
                          <form id="spectateGame" action="./" method="POST">
                              <button name="button" type="submit" value=${game.getGameID()}>Spectate Game</button>
                          </form>
                      </li>

              </#list>
          </ul>


      </div>

      <div style="width: 100%;">
          <h2>Replay Saved Games</h2>
          <#if gameSaves?size == 0>
              <p>You have no saved games on record</p>
          </#if>

          <ul>
              <#list gameSaves as game>
                  <li>${game.getName()}
                      <form id="replayGame" action="./" method="POST">
                          <button name="button" type="submit" value=${game.getGameID()}>Replay Game</button>
                      </form>
                  </li>

              </#list>
          </ul>


      </div>

    <#else>
        ${numPlayers} Players Logged In
    </#if>



  </div>

</div>
</body>

</html>
