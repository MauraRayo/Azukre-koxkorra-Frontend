document.addEventListener("DOMContentLoaded", () => {
  const contenedor = document.querySelector(".carrusel-contenedor");
  const prevBtn = document.querySelector(".btn-carrusel.prev");
  const nextBtn = document.querySelector(".btn-carrusel.next");
  const cards = document.querySelectorAll(".comentario-card");
  const totalCards = cards.length;
  let index = 0;

  const cardWidth = cards[0].offsetWidth + 40; // ancho de tarjeta + gap

  function updateCarousel() {
    contenedor.style.transform = `translateX(-${index * cardWidth}px)`;
  }

  // Avanzar al siguiente
  nextBtn.addEventListener("click", () => {
    index = (index + 1) % totalCards;
    updateCarousel();
  });

  // Retroceder al anterior
  prevBtn.addEventListener("click", () => {
    index = (index - 1 + totalCards) % totalCards;
    updateCarousel();
  });

  // === AUTOPLAY ===
  let autoplay = setInterval(() => {
    index = (index + 1) % totalCards;
    updateCarousel();
  }, 4000); // cada 4 segundos

  // Pausar autoplay al pasar el mouse
  const carrusel = document.querySelector(".carrusel");
  carrusel.addEventListener("mouseenter", () => clearInterval(autoplay));
  carrusel.addEventListener("mouseleave", () => {
    autoplay = setInterval(() => {
      index = (index + 1) % totalCards;
      updateCarousel();
    }, 4000);
  });
});
