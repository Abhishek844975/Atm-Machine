
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ATM Machine</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/atm.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
</head>
<body>
    <section class="atm">
        <form class="checksforms" action="${pageContext.request.contextPath}/CheckAmount" method="post">
            <input type="text" placeholder="user-name" name="user_name">
            <label>
                <select  class="option" name="option">
                    <option value="1" >Check-Balance</option>
                    <option value="2" >Withdraw-Money</option>
                    <option value="3" > Deposit Amount</option>
                </select>
            </label>

            <label>
                <select class="withdraw" name="withdrawAmount">
                 <option value="0"> withdrawAmount</option>
                    <option value="100">100</option>
                    <option value="200">200</option>
                    <option value="5000">5000</option>
                    <option value="10000">10000</option>
                </select>
            </label><br>
            <label>
            <select class="deposite" name="depositeAmount">
             <option value="0">DepositeAmount </option>
            <option value="100"> 100</option>
           <option value="200">200</option>
            <option value="500">500</option>
             <option value="10000">10000 </option>
            <option value="50000"> 50000</option>
            <option value="100000">100000</option>
            
            </select>
            </label>

            <div class="input-group">
                <i class="fa-solid fa-eye" onclick="showPassword()" style="cursor:pointer"></i>
                <input placeholder="enter-pin" type="password"  name="user_pin" class="enter-pin" id="pass">
            </div><br>

            <button type="submit"  class="btn">Check Balance</button>
        </form>
    </section>

    <script>
        function showPassword() {
            let x = document.getElementById("pass");
            x.type = (x.type === "password") ? "text" : "password";
        }
    </script>
</body>
</html>