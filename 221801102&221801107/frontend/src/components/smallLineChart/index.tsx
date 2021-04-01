import React from 'react';
import { Chart, Geom, Axis, Legend } from 'bizcharts';

interface SmallLineChart {
  data: any;
}

const SmallLineChart = (props: SmallLineChart) => {
  const { data } = props;
  const scale = {
    month: {
      range: [0, 1],
    },
  };
  return (
    <Chart data={data} scale={scale} autoFit padding={[10, 10, 50, 10]}>
      <Legend />
      <Axis name="month" visible={false} />
      <Axis name="value" visible={false} />
      <Geom
        type="line"
        position="month*value"
        size={2}
        color={'word'}
        shape={'smooth'}
      />
      <Geom
        type="point"
        position="month*value"
        size={4}
        shape={'circle'}
        color={'word'}
        style={{
          stroke: '#fff',
          lineWidth: 1,
        }}
      />
    </Chart>
  );
};

export default SmallLineChart;
