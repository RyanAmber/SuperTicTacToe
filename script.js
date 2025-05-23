document.getElementById('signin-form').addEventListener('submit', function(event) {
event.preventDefault();

const email = document.getElementById('email').value.trim();
const password = document.getElementById('password').value.trim();
const message = document.getElementById('message');

// Fake validation logic for demonstration
if (email === "user@example.com" && password === "password123") {
message.style.color = "green";
message.textContent = "Sign-in successful!";
} else {
message.style.color = "red";
message.textContent = "Invalid email or password.";
}
});
