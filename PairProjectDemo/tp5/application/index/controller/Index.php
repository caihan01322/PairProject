<?php
namespace app\index\controller;

use app\index\model\paper;
use app\index\model\test;
use think\Controller;
use think\Db;

class Index extends Controller
{
    public function index()
    {
        $data = paper::select();
        $jdata=json_encode($data);
        $sum=$data->count();
        if($sum%4>0){
            $total=$sum/4+1;
        }else{
            $total=$sum;
        }
        $total=$total*10;
        $this->assign([
            'sum'=>$sum,
            'jdata'=>$jdata,
            'total'=>$total
        ]);
        // $this->assign('a1','123');
        return $this->fetch();
        // return json($data);
    }

    public function hello($name = 'ThinkPHP5')
    {
        
        return 'hello,' . $name;
    }
    public function changePagination($index = 1)
    {
        $data = paper::select();
        $jdata=json_encode($data);
        $index=$index*4-4;
        $sum=$data->count();
        if($sum%4>0){
            $total=$sum/4+1;
        }else{
            $total=$sum;
        }
        $total=$total*10;
        $this->assign([
            'sum'=>$sum,
            'jdata'=>$jdata,
            'total'=>$total
        ]);
        $this->assign('jumpUrl', "javascript:window.parent.location.reload();");
        $this->success('提交成功'); 
        return $index;
        
    }
}
