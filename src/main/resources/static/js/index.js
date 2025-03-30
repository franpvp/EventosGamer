document.getElementById("register-form").addEventListener("submit", function(e) {
      e.preventDefault();
      // Recopilar valores del formulario
      const username = document.getElementById("register-username").value;
      const password = document.getElementById("register-password").value;
      const email = document.getElementById("register-email").value;
      const nombre = document.getElementById("register-nombre").value;
      const apellidoPaterno = document.getElementById("register-apellidoPaterno").value;
      const apellidoMaterno = document.getElementById("register-apellidoMaterno").value;
      const fechaNacimiento = document.getElementById("register-fechaNacimiento").value;

      // Valores por defecto
      const role = "USER"; // Se asigna por defecto el rol "USER"

      // Crear objeto usuario con todos los campos requeridos por la entidad
      const usuario = {
        username,
        password,
        email,
        nombre,
        apellidoPaterno,
        apellidoMaterno,
        fechaNacimiento,
        role
      };

      // Realizar solicitud POST al endpoint de tu REST controller
      fetch("http://localhost:8080/api/v1/usuarios", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(usuario)
      })
      .then(response => {
        if (response.ok) {
          alert("Usuario registrado exitosamente");
          // Opcional: redirigir a la página de inicio de sesión
          window.location.href = "/login";
        } else {
          alert("Error al registrar usuario");
        }
      })
      .catch(error => {
        console.error("Error:", error);
        alert("Error en la solicitud");
      });
    });