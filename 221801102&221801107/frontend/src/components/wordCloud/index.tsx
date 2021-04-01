import React from 'react';
import _ from 'lodash';
import data from './data';
import {
  Chart,
  Geom,
  Tooltip,
  Coordinate,
  Legend,
  Axis,
  Interaction,
  G2,
  registerShape,
} from 'bizcharts';
import DataSet from '@antv/data-set';
import { cloudData } from '@/models/statistic';

function getTextAttrs(cfg) {
  return _.assign({}, cfg.style, {
    fontSize: cfg.data.size,
    text: cfg.data.text,
    textAlign: 'center',
    fontFamily: cfg.data.font,
    fill: cfg.color,
    textBaseline: 'Alphabetic',
  });
}
registerShape('point', 'cloud', {
  draw(cfg, container) {
    const attrs = getTextAttrs(cfg);
    const textShape = container.addShape('text', {
      attrs: _.assign(attrs, {
        x: cfg.x,
        y: cfg.y,
      }),
    });
    if (cfg.data.rotate) {
      G2.Util.rotate(textShape, (cfg.data.rotate * Math.PI) / 180);
    }
    return textShape;
  },
});

interface WordCloudProps {
  data: cloudData[];
}

const WordCloud = (props: WordCloudProps) => {
  const { data } = props;
  if (data.length === 0) return null;
  const dv = new DataSet.View().source(data.slice(0, 30));
  const range = dv.range('value');
  const min = range[0];
  const max = range[1];
  dv.transform({
    type: 'tag-cloud',
    fields: ['name', 'value'],
    size: [600, 500],
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
        return ((d.value - min) / (max - min)) * (40 - 12) + 12;
      }
      return 0;
    },
  });
  const scale = {
    x: {
      nice: false,
    },
    y: {
      nice: false,
    },
  };
  return (
    <Chart data={dv.rows} scale={scale} padding={30} autoFit>
      <Tooltip showTitle={false} />
      <Coordinate reflect="y" />
      <Axis name="x" visible={false} />
      <Axis name="y" visible={false} />
      <Legend visible={false} />
      <Geom
        type="point"
        position="x*y"
        color="name"
        shape="cloud"
        tooltip="name*value"
      />
      <Interaction type="element-active" />
    </Chart>
  );
};

export default WordCloud;
