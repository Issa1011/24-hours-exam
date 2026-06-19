async function loadAlerts() {
    const response = await fetch("/api/alerts", {
        credentials: 'include'
    });
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

                ${alert.status === 'UNDER_REVIEW' ? `
                    <button onclick="updateStatus(${alert.id}, 'ACTIVE')">Godkend</button>
                    <button onclick="updateStatus(${alert.id}, 'FALSE_ALARM')">Falsk alarm</button>
                ` : ''}

                ${alert.status === 'ACTIVE' ? `
                    <button onclick="updateStatus(${alert.id}, 'NOT_ACTIVE')">Sæt inaktiv</button>
                ` : ''}

                <hr>
            </div>
        `;
    }
}

async function loadActiveAlerts() {
    const response = await fetch("/api/alerts/active", {
        credentials: 'include'
    });
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

async function updateStatus(id, status) {
    await fetch(`/api/alerts/${id}/status`, {
        method: 'PATCH',
        credentials: 'include',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ status: status })
    });
    loadAlerts();
}

async function loadReadings() {
    const response = await fetch("/api/sensor-data/readings", {
        credentials: "include"
    });

    if (!response.ok) {
        alert("Kun ADMIN har adgang");
        return;
    }

    const readings = await response.json();

    const container = document.getElementById("readings");
    container.innerHTML = "";

    for (const reading of readings) {
        container.innerHTML += `
            <div>
                <p>ID: ${reading.id}</p>
                <p>Magnitude: ${reading.estimatedMagnitude}</p>
                <p>Distance: ${reading.estimatedDistanceToEpicenterKm} km</p>
                <p>Sensor: ${reading.sensor.sensorId}</p>
                <hr>
            </div>
        `;
    }
}