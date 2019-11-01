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
        <h2>Players Online</h2>

        <#if playerList?size == 1>
            <p>There are no other players available to play at the time.</p>
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
    <#else>
        ${numPlayers} Players Logged In
    </#if>



  </div>

</div>
</body>

</html>
