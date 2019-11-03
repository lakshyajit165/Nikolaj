let dataToSend;
let dataToSend2;
function load(selected) {
        let matrix
        let matrix2
        let url;
       // console.log(selected);
        
        if (selected === 'NoCommand-0') {
              //  url = 'http://localhost:3002/data2';
              url='https://nikolaj-dev.stackroute.io/commandregistry/api/v1/commandregistry/reports/type?type=NoCommand';
              //  url=' http://localhost:9003/api/v1/commandregistry/reports/type?type=NoCommand';
                
        }
        else {
            // url = 'http://localhost:3001/data3';
             // url=' http://localhost:9003/api/v1/commandregistry/reports/type?type=NoIntent';
             url='https://nikolaj-dev.stackroute.io/commandregistry/api/v1/commandregistry/reports/type?type=NoIntent';
                
        }

        let format = d3.format(",d");
        let width = 932;
        let radius = width / 6;

        let arc = d3.arc()
                .startAngle(d => d.x0)
                .endAngle(d => d.x1)
                .padAngle(d => Math.min((d.x1 - d.x0) / 2, 0.005))
                .padRadius(radius * 1.5)
                .innerRadius(d => d.y0 * radius)
                .outerRadius(d => Math.max(d.y0 * radius, d.y1 * radius - 1));

        let partition = data => {
                let root = d3.hierarchy(data)
                        .sum(d => d.size)
                        .sort((a, b) => b.value - a.value);
                return d3.partition()
                        .size([2 * Math.PI, root.height + 1])
                        (root);
        }

        d3.json(url).then(data => {
         //      console.log(data.result);
                let root = partition(data.result);
                let color = d3.scaleOrdinal().range(d3.quantize(d3.interpolateRainbow, data.result.children.length + 1));

                root.each(d => d.current = d);

                let svg = d3.select('#partitionSVG')
                        .style("width", "100%")
                        .style("height", "auto")
                        .style("font", "25px sans-serif");
                       // .attr("transform", function(d) { return "translate(" + arc.centroid(d) + ")" + "rotate(" + getAngle(d) + ")"; });
                let g = svg.append("g")
                       .attr("transform", `translate(${width / 2},${width / 2})`);
                     // .attr("transform", function(d) { return "translate(" + arc.centroid(d) + ")" + "rotate(" + getAngle(d) + ")"; });

                let path = g.append("g")
                        .selectAll("path")
                        .data(root.descendants().slice(1))
                        .join("path")
                        .attr("fill", d => {
                                while (d.depth > 1)
                                        d = d.parent;
                                return color(d.data.name);
                        })
                        .attr("fill-opacity", d => arcVisible(d.current) ? (d.children ? 0.6 : 0.4) : 0)
                        .attr("d", d => arc(d.current));

                path.filter(d => d.children)
                        .style("cursor", "pointer")
                        .on("click", clicked);

                path.append("title")
                        .text(d => `${d.ancestors().map(d => d.data.name).reverse().join("/")}\n${format(d.value)}`);

                let label = g.append("g")
                        .attr("pointer-events", "none")
                        .attr("text-anchor", "middle")
                        .style("user-select", "none")
                        .selectAll("text")
                        .data(root.descendants().slice(1))
                        .join("text")
                        .attr("dy", "0.3838464em")
                        .attr("fill-opacity", d => +labelVisible(d.current))
                        .attr("transform", d => labelTransform(d.current))
                        .text(d => d.data.name);

                let parent = g.append("circle")
                        .datum(root)
                        .attr("r", radius)
                        .attr("fill", "none")
                        .attr("pointer-events", "all")
                        .on("click", clicked);

                function clicked(p) {
                        
                        // TrackIssueComponent.getAnyThing();
                        if(p.depth==3){
                                
                        dataToSend=p.data.children;
                        dataToSend2=p.data;
                        // sendData();
                        // console.log('data to send',dataToSend);
                         matrix = p.data.children;
                         matrix2=p.data;
                         console.log('hello intent',p.data);
                        //  dataToSend = matrix;  
                               let tr = d3.select(".querylist tbody")
                                .selectAll("tr")
                                .data(matrix)
                                .enter().append("tr");
                           
                               let td = tr.selectAll("td")
                                .data(function(d, i) { return Object.values(d); })
                                .enter().append("td")
                                  .text(function(d) { return d; });
                                  

                        
                        // dataToSend=p.data.children;
                        //  sendData(p.data.children);
                        //   console.log('data to send',dataToSend);
                                                  
                }
                // else{
                //          matrix = [];
                           
                //                tr = d3.select(".querylist tbody")
                //                 .selectAll("tr")
                //                 .data(matrix)
                //                 .enter().append("tr");
                           
                //                td = tr.selectAll("td")
                //                 .data(function(d, i) { return Object.values(d); })
                //                 .enter().append("td")
                //                   .text(function(d) { return d; });

                //         console.log('hello',p.data);
                //          this.dataToSend=p.data.children;
                //         //  sendData(p.data.children);
                        
                // }
                        parent.datum(p.parent || root);

                        root.each(d => d.target = {
                               // console.log("hello",d);
                                x0: Math.max(0, Math.min(1, (d.x0 - p.x0) / (p.x1 - p.x0))) * 2 * Math.PI,
                                x1: Math.max(0, Math.min(1, (d.x1 - p.x0) / (p.x1 - p.x0))) * 2 * Math.PI,
                                y0: Math.max(0, d.y0 - p.depth),
                                y1: Math.max(0, d.y1 - p.depth)
                        });

                        let t = g.transition().duration(750);
                        path.transition(t)
                                .tween("data", d => {
                                        const i = d3.interpolate(d.current, d.target);
                                        return t => d.current = i(t);
                                })
                                .filter(function (d) {
                                        return +this.getAttribute("fill-opacity") || arcVisible(d.target);
                                })
                                .attr("fill-opacity", d => arcVisible(d.target) ? (d.children ? 0.6 : 0.4) : 0)
                                .attrTween("d", d => () => arc(d.current));

                        label.filter(function (d) {
                                return +this.getAttribute("fill-opacity") || labelVisible(d.target);
                        }).transition(t)
                                .attr("fill-opacity", d => +labelVisible(d.target))
                                .attrTween("transform", d => () => labelTransform(d.current));
                }

                function arcVisible(d) {
                        return d.y1 <= 3 && d.y0 >= 1 && d.x1 > d.x0;
                }

                function labelVisible(d) {
                        return d.y1 <= 3 && d.y0 >= 1 && (d.y1 - d.y0) * (d.x1 - d.x0) > 0.03;
                }

                function labelTransform(d) {
                        const x = (d.x0 + d.x1) / 2 * 180 / Math.PI;
                        const y = (d.y0 + d.y1) / 2 * radius;
                        return `rotate(${x - 90}) translate(${y},0) rotate(${x < 180 ? 0 : 180})`;
                }
        });
        
        
        
}
// function sendData(){
        //         return this.p.data.children;
        // }

function sendData(){
        // console.log(data);
        // console.log(dataToSend)
        return dataToSend;
}

function sendDataCommand()
{
        console.log("data intent = " + dataToSend2);
        return dataToSend2;
}