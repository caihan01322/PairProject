import React from 'react';
import { Chart, LineAdvance } from 'bizcharts';

interface LineChartProps {
  data: any;
}

const LineChart = (props: LineChartProps) => {
  const { data } = props;
  return (
    <Chart padding={[10, 10, 100, 10]} autoFit data={data}>
      <LineAdvance
        shape="smooth"
        point
        area
        position="month*value"
        color="word"
      />
    </Chart>
  );
};

export default LineChart;
