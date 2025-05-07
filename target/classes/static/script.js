document.addEventListener("DOMContentLoaded", () => {
    // Login Modal Elements
    const modal = document.getElementById("loginModal");
    const openLogin = document.getElementById("openLogin");
    const closeModal = document.querySelector(".close");

    // Register Modal Elements
    const registerModal = document.getElementById("registerModal");
    const openRegister = document.getElementById("openRegister");
    const closeRegister = document.getElementById("closeRegister");
    const toLogin = document.getElementById("toLogin");

    // Open and close Login Modal
    openLogin.addEventListener("click", () => {
        modal.style.display = "flex";
    });

    closeModal.addEventListener("click", () => {
        modal.style.display = "none";
    });

    window.addEventListener("click", (e) => {
        if (e.target === modal) {
            modal.style.display = "none";
        }
    });

    // Login Form Submission
    const form = document.getElementById("loginForm");

    form.addEventListener("submit", (e) => {
        e.preventDefault();
        const email = form.querySelector("input[type='email']").value;
        const password = form.querySelector("input[type='password']").value;

        fetch("http://localhost:8081/api/login", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ email, password }),
        })
        .then(res => res.json())
        .then(data => {
            if (data.success) {
                alert("Login successful!");
                window.location.href = "/homepage.html";  // Redirect after successful login
            } else {
                alert("Login failed. Please try again.");
            }
        })
        .catch(err => {
            console.error("Error:", err);
            alert("An error occurred while logging in.");
        });
    });

    // Register Modal Logic
    openRegister.addEventListener("click", (e) => {
        e.preventDefault();
        modal.style.display = "none";  // Close login modal
        registerModal.style.display = "flex";  // Open register modal
    });

    closeRegister.addEventListener("click", () => {
        registerModal.style.display = "none";  // Close register modal
    });

    toLogin.addEventListener("click", (e) => {
        e.preventDefault();
        registerModal.style.display = "none";  // Close register modal
        modal.style.display = "flex";  // Open login modal
    });

    window.addEventListener("click", (e) => {
        if (e.target === registerModal) {
            registerModal.style.display = "none";  // Close register modal if clicking outside
        }
    });

  // Register Form Submission
  const registerForm = document.getElementById("registerForm");

  registerForm.addEventListener("submit", (e) => {
      e.preventDefault();

      const name = document.getElementById("regName").value.trim();
      const email = document.getElementById("regEmail").value.trim();
      const password = document.getElementById("regPassword").value.trim();
      const address = document.getElementById("regAddress").value.trim();
      const role = document.getElementById("regRole").value;

      // Validation: Ensure all fields are filled
      if (!name || !email || !password || !address || !role) {
          alert("Please fill in all fields.");
          return;
      }

      // ✅ Updated fetch logic here
      fetch("http://localhost:8081/api/register", {
          method: "POST",
          headers: {
              "Content-Type": "application/json",
          },
          body: JSON.stringify({ name, email, password, address, role }),
      })
      .then(res => {
          if (!res.ok) return res.json().then(err => { throw err });  // catch error JSON
          return res.json();
      })
      .then(data => {
          alert("Registration successful!");
          registerModal.style.display = "none";
          modal.style.display = "flex";
      })
      .catch(err => {
          alert(err.error || "Registration failed. Please try again.");
          console.error("Registration error:", err);
      });
  });
});
