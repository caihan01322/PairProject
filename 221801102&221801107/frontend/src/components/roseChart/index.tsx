import React from 'react';
import { Chart, Interval, Tooltip, Axis, Coordinate, Legend } from 'bizcharts';

interface RoseChartProps {
  data: any;
}

const RoseChart = (props: RoseChartProps) => {
  const { data } = props;

  return (
    <Chart data={data.slice(0, 14)} autoFit>
      <Coordinate type="polar" />
      <Axis visible={false} />
      <Tooltip showTitle={false} />
      <Legend position="right" />
      <Interval
        position="name*value"
        adjust="stack"
        element-highlight
        color="name"
        style={{
          lineWidth: 1,
          stroke: '#fff',
        }}
        label={false}
      />
    </Chart>
  );
};

export default RoseChart;
