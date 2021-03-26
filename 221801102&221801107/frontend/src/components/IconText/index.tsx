import React, { ReactNode } from 'react';
import { Space } from 'antd';

interface IconTextProps {
  icon: ReactNode;
  text: string;
}

const IconText = (props: IconTextProps) => {
  const { icon, text } = props;
  return (
    <Space>
      {icon}
      {text}
    </Space>
  );
};

export default IconText;
