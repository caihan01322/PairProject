<template>
    <div id="hot_page">
        <div id="chart_part">
            <div id="pie_chart" ref="pie"></div>
            <div id="radar_chart" ref="radar"></div>
        </div>
        <div id="line_chart" ref="line"></div>
    </div>
</template>

<script>
export default {
    name: "Hotpage",

    data() {
        return {
            hot_word: "yeah",
            year: [
                "2012",
                "2013",
                "2014",
                "2015",
                "2016",
                "2017",
                "2018",
                "2019",
                "2020",
                "2021",
            ],
            cv_data: [120, 132, 101, 134, 90, 230, 210, 320, 332, 301],
            ic_data: [220, 182, 191, 234, 290, 330, 310, 334, 390, 330],
            ec_data: [150, 232, 201, 154, 190, 330, 410, 820, 932, 901],
            pie_data: [
                { value: 1048, name: "搜索引擎" },
                { value: 735, name: "直接访问" },
                { value: 580, name: "邮件营销" },
                { value: 484, name: "联盟广告" },
                { value: 300, name: "视频广告" },
            ],
            radar_tag: [
                { name: "销售（sales）" },
                { name: "管理（Administration）" },
                {
                    name: "信息技术（Information Techology）",
                },
                { name: "客服（Customer Support）" },
                { name: "研发（Development）" },
            ],
            radar_data: [
                {
                    value: [4300, 10000, 28000, 35000, 50000],
                    name: "CVPR",
                },
                {
                    value: [5000, 14000, 28000, 31000, 42000],
                    name: "ICCV",
                },
                {
                    value: [3000, 14000, 18000, 10000, 12000],
                    name: "ECCV",
                },
            ],
        };
    },

    created() {
        this.$axios({
            method: "GET",
            url: `/tag/pie`,
        }).then((re) => {
            console.log(re);
            if (re.data.error == 0) {
                let { pie_data, radar_data } = re.data.data;
                this.pie_data = pie_data;
                this.radar_tag = radar_data;
            }
        });

        this.$axios({
            method: "GET",
            url: `/tag/line`,
        }).then((re) => {
            console.log(re);
            if (re.data.error == 0) {
                let {
                    ic_data,
                    cv_data,
                    hot_word,
                    ec_data,
                    year,
                } = re.data.data;
                this.ic_data = ic_data;
                this.cv_data = cv_data;
                this.hot_word = hot_word;
                this.ec_data = ec_data;
                this.year = year;
            }
        });

        this.$axios({
            method: "GET",
            url: `/tag/radar`,
        }).then((re) => {
            console.log(re);
            if (re.data.error == 0) {
                this.radar_data = re.data.data;
            }
        });
    },

    mounted() {
        this.draw_pie();
        this.draw_radar();
        this.draw_line();
    },

    methods: {
        draw_pie() {
            let pie = this.$echarts.init(this.$refs.pie);
            pie.setOption({
                color: ["#879AD7", "#B1DB9E", "#FAC858", "#F29292", "#9CD3E8"],
                title: {
                    text: "总TOP5热词占比",
                    left: "4%",
                    top: "5%",
                    textStyle: {
                        color: "#033",
                    },
                },
                tooltip: {
                    trigger: "item",
                    show: true,
                    transitionDuration: 0,
                },
                legend: {
                    orient: "vertical",
                    left: "80%",
                    top: "10%",
                },
                series: [
                    {
                        type: "pie",
                        radius: "65%",
                        center: ["48%", "55%"],
                        data: this.pie_data,
                        emphasis: {
                            itemStyle: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: "rgba(0, 0, 0, 0.5)",
                            },
                        },
                    },
                ],
            });
        },

        draw_radar() {
            let radar = this.$echarts.init(this.$refs.radar);
            radar.setOption({
                color: ["#F7AE42", "#CA33A8", "#7570C7"],
                title: {
                    text: "本年三大顶会总TOP5热度对比",
                    top: "5%",
                    textStyle: {
                        color: "#033",
                    },
                },
                tooltip: {
                    confine: true,
                    enterable: true,
                    show: true,
                    transitionDuration: 0,
                },
                legend: {
                    orient: "vertical",
                    data: ["CVPR", "ICCV", "ECCV"],
                    right: "4%",
                    top: "4%",
                },
                radar: {
                    name: {
                        textStyle: {
                            color: "#FCFDF5",
                            backgroundColor: "#9ECCA4",
                            borderRadius: 3,
                            padding: [3, 5],
                        },
                    },
                    center: ["50%", "55%"],
                    indicator: this.radar_tag,
                },
                series: [
                    {
                        type: "radar",
                        data: this.radar_data,
                    },
                ],
            });
        },

        draw_line() {
            let line = this.$echarts.init(this.$refs.line);
            line.setOption({
                color: ["#3AA1FE", "#4ECB72", "#FADA57"],
                title: {
                    text: `近十年热词${this.hot_word}走势对比`,
                    left: "center",
                    top: "4%",
                    textStyle: {
                        color: "#033",
                    },
                },
                tooltip: {
                    trigger: "axis",
                    show: true,
                    transitionDuration: 0,
                },
                legend: {
                    data: ["CVPR", "ICCV", "ECCV"],
                    right: "4%",
                    top: "4%",
                },
                grid: {
                    left: "10%",
                    right: "10%",
                    bottom: "10%",
                    containLabel: true,
                },
                xAxis: {
                    type: "category",
                    data: this.year,
                },
                yAxis: {
                    type: "value",
                },
                series: [
                    {
                        name: "CVPR",
                        type: "line",
                        smooth: true,
                        stack: "1",
                        data: this.cv_data,
                    },
                    {
                        name: "ICCV",
                        type: "line",
                        smooth: true,
                        stack: "1",
                        data: this.ic_data,
                    },
                    {
                        name: "ECCV",
                        type: "line",
                        smooth: true,
                        stack: "1",
                        data: this.ec_data,
                    },
                ],
            });
        },
    },
};
</script>

<style scoped>
#hot_page {
    /* border: 1px red solid;
    box-sizing: border-box; */
    width: 100%;
    height: 90%;
    overflow: hidden;
}

#chart_part {
    /* border: 1px green solid;
    box-sizing: border-box; */
    display: flex;
    justify-content: space-between;
    width: 100%;
    height: 50%;
}

#pie_chart {
    /* border: 1px red solid;
    box-sizing: border-box; */
    width: 50%;
    height: 100%;
}

#radar_chart {
    /* border: 1px blue solid;
    box-sizing: border-box; */
    width: 50%;
    height: 100%;
}

#line_chart {
    /* border: 1px purple solid;
    box-sizing: border-box; */
    width: 100%;
    height: 50%;
}
</style>