import React, { ReactNode } from 'react';
import { Space, Button } from 'antd';

type IconTextProps = {
  icon: ReactNode;
  text: string;
  onClick:
    | ((event: React.MouseEvent<HTMLSpanElement, MouseEvent>) => void)
    | undefined;
};

const IconText = (props: IconTextProps) => {
  const { icon, text, onClick } = props;
  return (
    <Button type="link" onClick={onClick}>
      <Space>
        {icon}
        {text}
      </Space>
    </Button>
  );
};

export default IconText;
