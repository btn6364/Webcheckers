 <div class="navigation">
  <#if currentUser??>
      <a href="/">my home</a> |
      <form id="signout" action="./signout" method="POST">
<#--        <a href="./signout" onclick="signout.submit();">sign out [${currentUser.name}]</a>-->
            <input type="submit" value="sign out [${currentUser.name}]">
      </form>
  <#else>
    <a href="./signin">sign in</a>
  </#if>
 </div>
