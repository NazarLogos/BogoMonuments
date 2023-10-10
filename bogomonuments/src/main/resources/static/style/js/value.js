document.getElementById("value").addEventListener("change", function() {
    var selectedValue = this.value;
    var currentUrl = window.location.href;

    // Регулярний вираз для пошуку параметра "value" в URL
    var valueRegex = /[?&]value=([^&]*)/;

    // Перевірка, чи параметр "value" вже присутній в URL
    var match = currentUrl.match(valueRegex);

    if (match) {
        // Якщо параметр "value" вже присутній, оновлюємо його значення
        var updatedUrl = currentUrl.replace(valueRegex, "?value=" + selectedValue);
        window.location.href = updatedUrl;
    } else {
        // Якщо параметр "value" ще не існує, додаємо його до URL
        if (currentUrl.indexOf("?") !== -1) {
            window.location.href = currentUrl + "&value=" + selectedValue;
        } else {
            window.location.href = currentUrl + "?value=" + selectedValue;
        }
    }
});