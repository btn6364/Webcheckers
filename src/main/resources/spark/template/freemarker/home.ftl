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


  <#if player??>

        <ul>
        <#list lobby.getPlayers as otherPlayers>
            <#if otherPlayers.getName != player.getName>
                <li>$(otherPlayers.getName()</li>
            <#/if>
        </#list>
        </ul>

        <#else>
            $(lobby.numPlayers()) Players Logged In
        </#if>



  </div>

</div>
</body>

</html>
