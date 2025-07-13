document.getElementById('gameSetupForm').addEventListener('submit', function(event) {
event.preventDefault();

const player1 = document.getElementById('player1').value.trim();
const player2 = document.getElementById('player2').value.trim();

// Fake validation logic for demonstration
if (email === "user@example.com" && password === "password123") {
message.style.color = "green";
message.textContent = "Sign-in successful!";
} else {
message.style.color = "red";
message.textContent = "Invalid email or password.";
}
});
document.addEventListener('DOMContentLoaded', function() {
  const modal = document.getElementById('rulesModal');
  const btn = document.getElementById('rules');
  const span = document.querySelector('.close');

  btn.onclick = function() {
    modal.style.display = 'block';
  }

  span.onclick = function() {
    modal.style.display = 'none';
  }

  window.onclick = function(event) {
    if (event.target == modal) {
      modal.style.display = 'none';
    }
  }
});
