<!--  -->
<template>
    <div id='wordCloud'></div>
</template>

<script>

import DataSet from '@antv/data-set';
import { Chart, registerShape, Util } from '@antv/g2';

function getTextAttrs(cfg) {
  return {
    ...cfg.defaultStyle,
    ...cfg.style,
    fontSize: cfg.data.size,
    text: cfg.data.text,
    textAlign: 'center',
    fontFamily: cfg.data.font,
    fill: cfg.color || cfg.defaultStyle.stroke,
    textBaseline: 'Alphabetic'
  };
}

// 给point注册一个词云的shape
registerShape('point', 'cloud', {
  draw(cfg, container) {
    const attrs = getTextAttrs(cfg);
    const textShape = container.addShape('text', {
      attrs: {
        ...attrs,
        x: cfg.x,
        y: cfg.y
      }
    });
    if (cfg.data.rotate) {
      Util.rotate(textShape, cfg.data.rotate * Math.PI / 180);
    }
    return textShape;
  }
});



export default {
    name: 'wordCloud',
    components: {},
    data () {
        return {

        }
    },
    mounted() {
        let words = [
            {
                "x": "关键词1",
                "value": 5,
            },
            {
                "x": "关键词2",
                "value": 3,
            },
            {
                "x": "关键词3",
                "value": 2,
            },
        ];
        const dv = new DataSet.View().source(words);
        const range = dv.range('value');
        const min = range[0];
        const max = range[1];
        dv.transform({
            type: 'tag-cloud',
            fields: ['x', 'value'],
            size: [500, 400],
            font: 'Verdana',
            padding: 0,
            timeInterval: 5000, // max execute time
            rotate() {
                let random = ~~(Math.random() * 4) % 4;
                if (random === 2) {
                    random = 0;
                }
                return random * 90; // 0, 90, 270
            },
            fontSize(d) {
                if (d.value) {
                    return ((d.value - min) / (max - min)) * (80 - 24) + 24;
                }
                return 0;
            }
        });
        const chart = new Chart({
            container: 'wordCloud',
            autoFit: false,
            width: 500,
            height: 400,
            padding: 0
        });
        chart.data(dv.rows);
        chart.scale({
            x: { nice: false },
            y: { nice: false }
        });
        chart.legend(false);
        chart.axis(false);
        chart.tooltip({
            showTitle: false,
            showMarkers: false
        });
        chart.coordinate().reflect();
        chart.point()
            .position('x*y')
            .color('CornflowerBlue')
            .shape('cloud')
            .tooltip('value*category');
        chart.interaction('element-active');
        chart.render();
    },
}
</script>

<style lang='scss' scoped>
//@import url();
#wordCloud {
    width: 500px;
    height: 400px;
}
</style>