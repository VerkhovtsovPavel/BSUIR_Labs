window.chartColors = {
    red: 'rgb(255, 99, 132)',
    orange: 'rgb(255, 159, 64)',
    yellow: 'rgb(255, 205, 86)',
    green: 'rgb(75, 192, 192)',
    blue: 'rgb(54, 162, 235)',
    purple: 'rgb(153, 102, 255)',
    grey: 'rgb(201, 203, 207)',
    lime: 'rgb(128, 128, 0)',
    brown: 'rgb(170, 110, 40)',
    mint: 'rgb(170, 255, 195)'
};

var chartColors = window.chartColors;
var color = Chart.helpers.color;
var config = {
    data: {
        datasets: [{
            data: [
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0
            ],
            backgroundColor: [
            color(chartColors.red).alpha(0.5).rgbString(),
            color(chartColors.orange).alpha(0.5).rgbString(),
            color(chartColors.yellow).alpha(0.5).rgbString(),
            color(chartColors.green).alpha(0.5).rgbString(),
            color(chartColors.blue).alpha(0.5).rgbString(),
            color(chartColors.purple).alpha(0.5).rgbString(),
            color(chartColors.grey).alpha(0.5).rgbString(),
            color(chartColors.brown).alpha(0.5).rgbString(),
            color(chartColors.mint).alpha(0.5).rgbString(),
            ],
            label: 'My dataset'
        }],
        labels: [
        "Time",
        "Lines",
        "Square",
        "Horizontal Length",
        "Vertical Length",
        "Total Length",
        "Max Velocity",
        "Min Velocity",
        "Duration by X",
        "Duration by Y"
        ]
    },
    options: {
        responsive: true,
        legend: {
            position: 'right',
        },
        title: {
            display: true,
            text: 'Sign params'
        },
        scale: {
            ticks: {
                beginAtZero: true
            },
            reverse: false
        },
        animation: {
            animateRotate: false,
            animateScale: true
        }
    }
};

function updateChart(timer , lines, square, horizontalLength, verticalLength, totalLength, maxVelocity, minVelocity, durationX, durationY) {
    config.data.datasets[0].data[0] = timer / 1000;
    config.data.datasets[0].data[1] = lines;
    config.data.datasets[0].data[2] = square / 10000;
    config.data.datasets[0].data[3] = horizontalLength / 100;
    config.data.datasets[0].data[4] = verticalLength / 100;
    config.data.datasets[0].data[5] = totalLength / 1000;
    config.data.datasets[0].data[6] = maxVelocity;
    config.data.datasets[0].data[7] = minVelocity * 1000;
    config.data.datasets[0].data[8] = durationX / 1000;
    config.data.datasets[0].data[9] = durationY / 1000;
    window.myPolarArea.update();
}

function initChart() {
    var ctx = document.getElementById("chart-area");
    window.myPolarArea = Chart.PolarArea(ctx, config);
}