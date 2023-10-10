const modal = document.getElementById("myModal");
const btn = document.querySelector(".open-modal-btn");
const closeBtn = document.querySelector(".close");
const modalContent = document.querySelector(".modal-content");

// Отримуємо код товару з URL
function getProductCodeFromUrl() {
    const url = window.location.href;
    const match = url.match(/\/(\d+)\/view/);
    if (match) {
        return match[1];
    }
    return null;
}

// Функція, яка оновлює текст умов покупки з кодом товару
function updateConditionsText() {
    const productCode = getProductCodeFromUrl();
    if (productCode) {
        modalContent.querySelector("p").textContent = `Для того щоб зробити замовлення зателефонуйте за номер телфону -
                +380-68-7777-77-77.
                 При замовлення вкажіть код товару (${productCode}) та особисті побажання.`;
    } else {
        modalContent.querySelector("p").textContent = "Умови покупки: ваш текст тут.";
    }
}

// Показуємо модальне вікно та оновлюємо текст при натисканні на кнопку
btn.addEventListener("click", function() {
    updateConditionsText();
    modal.style.display = "block";
});

// Закриваємо модальне вікно при натисканні на хрестик
closeBtn.addEventListener("click", function() {
    modal.style.display = "none";
});

// Закриваємо модальне вікно при натисканні за межами вікна
window.addEventListener("click", function(event) {
    if (event.target === modal) {
        modal.style.display = "none";
    }
});

// Викликаємо функцію для оновлення тексту при завантаженні сторінки
window.addEventListener("load", updateConditionsText);