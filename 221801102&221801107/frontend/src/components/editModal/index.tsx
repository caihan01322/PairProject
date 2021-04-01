import React from 'react';
import { Modal, Form, Input, Button } from 'antd';
import { FormInstance } from 'antd/lib/form';

import { setClsPrefixHOC } from '@/utils/setClsPrefixHOC';
import Prefix from '../constants';

const setClsPrefix = setClsPrefixHOC(Prefix.editModal);

const layout = {
  labelCol: { span: 4 },
  wrapperCol: { span: 20 },
};
const tailLayout = {
  wrapperCol: { offset: 4, span: 20 },
};

interface EditModalProps {
  visible: boolean;
  setVisible: React.Dispatch<React.SetStateAction<boolean>>;
  onFinish: any;
  initialValues: { title: string; content: string };
  loading: boolean;
}

const EditModal = ({
  visible,
  setVisible,
  initialValues,
  onFinish,
  loading,
}: EditModalProps) => {
  const [form] = Form.useForm<FormInstance>();
  const onReset = () => {
    form.resetFields();
  };
  return (
    <Modal
      centered
      title="编辑框"
      visible={visible}
      className={setClsPrefix()}
      footer={null}
      onCancel={(e) => setVisible(false)}
    >
      <Form
        {...layout}
        form={form}
        onFinish={onFinish}
        initialValues={initialValues}
      >
        <Form.Item
          name="title"
          label="标题"
          rules={[{ required: true, message: '请填入标题' }]}
        >
          <Input />
        </Form.Item>
        <Form.Item
          name="content"
          label="概要"
          rules={[{ required: true, message: '请填入概要' }]}
        >
          <Input.TextArea />
        </Form.Item>
        <Form.Item {...tailLayout}>
          <Button type="primary" htmlType="submit" loading={loading}>
            提交
          </Button>
          <Button htmlType="button" onClick={onReset}>
            重置
          </Button>
        </Form.Item>
      </Form>
    </Modal>
  );
};

export default EditModal;
