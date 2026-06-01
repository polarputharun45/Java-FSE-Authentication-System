// =====================
// REGISTER USER
// =====================

async function registerUser() {

    const user = {
        username: document.getElementById("username").value,
        email: document.getElementById("email").value,
        password: document.getElementById("password").value
    };

    try {

        const response = await fetch(
            "http://localhost:8080/api/auth/register",
            {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(user)
            }
        );

        const data = await response.json();

        alert("Registration Successful!");

        console.log(data);

        window.location.href = "login.html";

    } catch(error) {

        alert("Registration Failed!");

        console.log(error);
    }
}


// =====================
// LOGIN USER
// =====================

async function loginUser() {

    const user = {
        email: document.getElementById("loginEmail").value,
        password: document.getElementById("loginPassword").value
    };

    try {

        const response = await fetch(
            "http://localhost:8080/api/auth/login",
            {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(user)
            }
        );

        const result = await response.text();

        alert(result);

        if(result === "Login Successful") {
            window.location.href = "dashboard.html";
        }

    } catch(error) {

        alert("Login Failed!");

        console.log(error);
    }
}


// =====================
// FORGOT PASSWORD
// =====================

async function forgotPassword() {

    const email =
        document.getElementById("forgotEmail").value;

    try {

        const response = await fetch(
            "http://localhost:8080/api/auth/forgot-password",
            {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    email: email
                })
            }
        );

        const result = await response.text();

        alert(result);

        if(result === "OTP Sent To Email") {

            window.location.href =
                "reset-password.html";
        }

    } catch(error) {

        alert("Error Occurred!");

        console.log(error);
    }
}


// =====================
// RESET PASSWORD
// =====================

async function resetPassword() {

    const user = {
        otp: document.getElementById("otp").value,
        password: document.getElementById("newPassword").value
    };

    try {

        const response = await fetch(
            "http://localhost:8080/api/auth/reset-password",
            {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(user)
            }
        );

        const result = await response.text();

        alert(result);

        if(result === "Password Reset Successful") {

            window.location.href =
                "login.html";
        }

    } catch(error) {

        alert("Error Occurred!");

        console.log(error);
    }
}


// =====================
// LOGOUT
// =====================

function logoutUser() {

    alert("Logged Out Successfully");

    window.location.href = "login.html";
}