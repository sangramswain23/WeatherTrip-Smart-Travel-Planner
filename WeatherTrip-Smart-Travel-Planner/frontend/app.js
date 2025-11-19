document.getElementById("themeToggle").onclick = () => {
    document.body.classList.toggle("dark");
};

async function planTrip() {
    const city = document.getElementById("cityInput").value;
    if (!city) return alert("Enter a city!");

    document.getElementById("loading").style.display = "block";
    document.getElementById("result").innerHTML = "";

    const token = "Bearer " + localStorage.getItem("jwt"); // optional

    try {
        const response = await fetch(`http://localhost:8080/api/trip/plan?city=${city}`, {
            headers: { "Authorization": token }
        });

        const data = await response.json();
        document.getElementById("loading").style.display = "none";

        showResult(data);

    } catch (e) {
        document.getElementById("loading").style.display = "none";
        alert("Error loading data");
    }
}

function showResult(data) {
    let html = `

        <div class="box">
            <h2>🌦 Weather in ${data.city}</h2>
            <p><b>Temperature:</b> ${data.weather.temperature}°C</p>
            <p><b>Condition:</b> ${data.weather.description}</p>
        </div>

        <div class="box">
            <h2>🌴 Nearby Attractions</h2>
    `;

    data.attractions.forEach(a => {
        html += `<div class="attraction">📍 ${a.properties.name || "Unnamed Place"}</div>`;
    });

    html += `</div>
        <div class="box">
            <h2>🕒 Best Time to Visit</h2>
            <p>${data.bestTime}</p>
        </div>
    `;

    document.getElementById("result").innerHTML = html;
}
