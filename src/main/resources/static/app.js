async function loadAlerts() {

    const response = await fetch("/api/alerts", {
        credentials: "include"
    });

    if (!response.ok) {
        alert("Admin access required");
        return;
    }

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

                <button type="button" onclick="loadReports(${alert.id})">
                    Show reports
                </button>

                ${alert.status === "UNDER_REVIEW" ? `
                    <button type="button" onclick="updateStatus(${alert.id}, 'ACTIVE')">
                        Godkend
                    </button>

                    <button type="button" onclick="updateStatus(${alert.id}, 'FALSE_ALARM')">
                        Falsk alarm
                    </button>
                ` : ""}


                ${alert.status === "ACTIVE" ? `
                    <button type="button" onclick="updateStatus(${alert.id}, 'NOT_ACTIVE')">
                        Sæt inaktiv
                    </button>
                ` : ""}

                <hr>
            </div>
        `;
    }
}



async function loadActiveAlerts() {

    const response = await fetch("/api/alerts/active", {
        credentials: "include"
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

                <input type="number"
                       id="intensity-${alert.id}"
                       min="1"
                       max="10"
                       placeholder="Intensitet">


                <button type="button" onclick="createReport(${alert.id})">
                    Opret rapport
                </button>

                <hr>
            </div>
        `;
    }
}



async function updateStatus(id, status) {

    await fetch(`/api/alerts/${id}/status`, {
        method: "PATCH",
        credentials: "include",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            status: status
        })
    });

    loadAlerts();
}



async function loadReadings() {

    const response = await fetch("/api/sensor-data/readings", {
        credentials: "include"
    });


    if (!response.ok) {
        alert("Admin access required");
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



async function createReport(alertId) {

    const intensityInput =
        document.getElementById(`intensity-${alertId}`);


    if (!intensityInput.value) {
        alert("Skriv intensitet først");
        return;
    }


    const response = await fetch(`/api/alerts/${alertId}/reports`, {

        method: "POST",

        credentials: "include",

        headers: {
            "Content-Type": "application/json"
        },

        body: JSON.stringify({
            intensity: Number(intensityInput.value)
        })
    });


    if (!response.ok) {
        alert("Kun én rapport pr varsel");
        return;
    }


    alert("Rapport oprettet");
}



async function loadReports(alertId) {

    const response = await fetch(`/api/alerts/${alertId}/reports`, {
        credentials: "include"
    });


    if (!response.ok) {
        alert("Admin access required");
        return;
    }


    const reports = await response.json();


    const container = document.getElementById("readings");

    container.innerHTML = "<h3>User Reports</h3>";


    if (reports.length === 0) {

        container.innerHTML += `
            <p>No report for this alert</p>
        `;

        return;
    }


    for (const report of reports) {

        container.innerHTML += `
            <div>
                <p>Report ID: ${report.id}</p>
                <p>Intensity: ${report.intensity}</p>
                <p>Created: ${report.createdAt}</p>
            </div>

            <hr>
        `;
    }
}