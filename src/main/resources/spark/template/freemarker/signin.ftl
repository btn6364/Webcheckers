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

    <h2>User Sign In</h2>

    <form id="signin" action="./signin" method="POST">
      <input type="text" name="username" value="">
      <br>
      <input type="submit" value="Submit">
    </form>

    <p>Enter a username and click the "submit" button to sign in!</p>


  </div>

</div>
</body>

</html>
