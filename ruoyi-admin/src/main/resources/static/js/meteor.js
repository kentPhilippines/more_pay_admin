const canvas = document.querySelector('canvas');
const ctx = canvas.getContext('2d');

canvas.width = 400;
canvas.height = 300;

class NewMeteor {
    constructor(start, end, speed = 3, cb = null) {
        this.startX = start[0];
        this.startY = start[1];
        this.endX = end[0];
        this.endY = end[1];
        this.init = start;
        this.final = end;
        this.speed = speed;
        this.cb = cb;
        this.tails = [start];
        this.die = false;
        // time line vars
        // flying time
        this.flyTime = Math.sqrt(Math.pow(this.startX - this.endX, 2) + Math.pow(this.startY - this.endY, 2)) * 1000 / this.speed;
        // passed time
        this.passedTime = 0;
    }
    move(delta = this.speed / 50) {
        if (this.die) {
            if (this.tails.length > 0) {
                this.tails.shift();
                return this.tails.map((tail, index) => ({
                    x: tail[0],
                    y: tail[1],
                    alpha: index * 10
                })).reverse();
            } else {
                this.cb && this.cb();
            }
        } else {
            this.passedTime = Math.min(this.passedTime += delta, this.flyTime);
            if (this.passedTime === this.flyTime) {
                this.die = true;
            }
            const timePercent = this.passedTime / this.flyTime;
            const deltaX = this.final[0] - this.init[0];
            const deltaY = this.final[1] - this.init[1];
            this.tails.push([
                this.startX + deltaX * timePercent,
                this.startY + deltaY * timePercent
            ]);
            if (this.tails.length > 11) {
                this.tails = this.tails.slice(-11);
            }
            return this.tails.concat().reverse().map((tail, index) => ({
                x: tail[0],
                y: tail[1],
                alpha: 100 - index * 10
            }));
        }
    }
}

const ani = () => {
    console.log('animation start!');
    let T;
    let i = 0;
    const star = new NewMeteor(
        [10, 10],
        [200, 200],
        400,
        () => {
            console.log('finished!');
            cancelAnimationFrame(T);
        }
    );
    ctx.lineCap = 'round';
    ctx.lineWidth = 3.5;
    // ctx.strokeStyle = 'blue';
    const loop = () => {
        ctx.clearRect(0, 0, ctx.canvas.width, ctx.canvas.height)
        T = requestAnimationFrame(loop);
        const starsMsg = star.move();
        // console.log(starsMsg);
        if (!starsMsg || starsMsg.length === 0) {
            return;
        }
        ctx.beginPath();
        // -----------------------------------------
        // ctx.moveTo(starsMsg[0].x, starsMsg[0].y);
        // starsMsg.shift();
        // starsMsg.forEach(point => {
        // 	ctx.save();
        // 	ctx.lineTo(point.x, point.y);
        // 	ctx.globalAlpha = point.alpha / 300;
        // 	ctx.stroke();
        // 	ctx.restore();
        // });
        // ---------------- way1 -------------------
        const start = starsMsg[0];
        const end = starsMsg.pop();
        const gradient = ctx.createLinearGradient(start.x, start.y, end.x, end.y);
        gradient.addColorStop(1,'transparent');
        gradient.addColorStop(0,'blue');
        ctx.strokeStyle = gradient;
        ctx.moveTo(start.x, start.y);
        ctx.lineTo(end.x, end.y);
        ctx.stroke();

        // if (++i > 13) {
        // 	// cancelAnimationFrame(T);
        // }
    }
    loop();
    function draw() {
        const way = [{
            "x": 149.37074657186852,
            "y": 149.37074657186852,
            "alpha": 100
        }, {
            "x": 147.46155826266482,
            "y": 147.46155826266482,
            "alpha": 90
        }, {
            "x": 145.55236995346115,
            "y": 145.55236995346115,
            "alpha": 80
        }, {
            "x": 143.64318164425748,
            "y": 143.64318164425748,
            "alpha": 70
        }, {
            "x": 141.7339933350538,
            "y": 141.7339933350538,
            "alpha": 60
        }, {
            "x": 139.8248050258501,
            "y": 139.8248050258501,
            "alpha": 50
        }, {
            "x": 137.91561671664644,
            "y": 137.91561671664644,
            "alpha": 40
        }, {
            "x": 136.00642840744274,
            "y": 136.00642840744274,
            "alpha": 30
        }, {
            "x": 134.09724009823907,
            "y": 134.09724009823907,
            "alpha": 20
        }, {
            "x": 132.1880517890354,
            "y": 132.1880517890354,
            "alpha": 10
        }, {
            "x": 130.27886347983173,
            "y": 130.27886347983173,
            "alpha": 0
        }]
        // way.slice().forEach((point, index) => {
        // 	// ctx.save();
        // 	ctx.globalAlpha = point.alpha/200;
        // 	console.log(ctx.globalAlpha)
        // 	ctx.lineTo(point.x, point.y);
        // 	ctx.moveTo(point.x, point.y);
        // 	// if (index % 2 == 0)
        // 	ctx.stroke();
        // 	// ctx.restore();
        // 	// ctx.moveTo(point.x, point.y);
        // });
        const gradient = ctx.createLinearGradient(way[0].x,way[0].y,way[10].x,way[10].y);
        gradient.addColorStop(1,'transparent')
        gradient.addColorStop(0,'blue')
        ctx.strokeStyle = gradient;
        ctx.moveTo(way[0].x,way[0].y);
        ctx.lineTo(way[10].x,way[10].y);
        ctx.stroke();
    }
    // draw();
}

canvas.onclick = ani;