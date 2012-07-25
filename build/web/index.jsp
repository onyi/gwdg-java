<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>Doodle Poster Store</title>
    <link href="/stylesheets/screen.css" media="all" rel="stylesheet" type="text/css"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"></meta>
    <script language="javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="http://www.google.com/jsapi"></script>
    <script type="text/javascript">
      google.load('payments', '1.0', {
        'packages': ['sandbox_config']
      });

      // Success handler
      var successHandler = function(status){
        if (window.console != undefined) {
          console.log("Purchase completed successfully: ", status);
          //window.location.reload();
        }
      }

      // Failure handler
      var failureHandler = function(status){
        if (window.console != undefined) {
          console.log("Purchase failed ", status);
        }
      }

      function purchase(item) {
        var generated_jwt;
        if (item == "Item1") {
          generated_jwt = "<%= request.getParameter("token") %>";
        }  else {
          return;
        }

        goog.payments.inapp.buy({
          'jwt'     : generated_jwt,
          'success' : successHandler,
          'failure' : failureHandler
        });
      }
    </script>
  </head>
  <body>
    <h1>Doodle Poster Store</h1>
    <table>
    <tr valign="top">
      <td>
      <ul>
        <li>
          <img src="images/drivein12.jpg"/>
          <button class="buy-button" type="button" onClick="purchase('Item1');">Purchase</button></li>
          <br/>
      </ul>
      </td>
    </tr>
    </table>
  </body>
</html>