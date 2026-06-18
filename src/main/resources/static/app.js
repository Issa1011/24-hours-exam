async function loadAlerts() {

    const response = await fetch("/api/alerts");

    const alerts = await response.json();

    const container = document.getElementById("alerts");

    container.innerHTML = "";

    for (const alert of alerts) {

        container.innerHTML += `
            <div>
                <h3>Alert ${alert.id}</h3>

                <p>Status: ${alert.status}</p>
                <p>Magnitude: ${alert.estimatedMagnitude}</p>
                <p>Area: ${alert.areaName}</p>

                <hr>
            </div>
        `;
    }
}

async function loadActiveAlerts() {

    const response = await fetch("/api/alerts/active");

    const alerts = await response.json();

    const container = document.getElementById("alerts");

    container.innerHTML = "";

    for (const alert of alerts) {

        container.innerHTML += `
            <div>
                <h3>Alert ${alert.id}</h3>

                <p>Status: ${alert.status}</p>
                <p>Magnitude: ${alert.estimatedMagnitude}</p>
                <p>Area: ${alert.areaName}</p>

                <hr>
            </div>
        `;
    }
}