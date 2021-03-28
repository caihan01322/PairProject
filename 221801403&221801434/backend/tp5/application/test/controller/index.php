<?php
namespace app\test\controller;

use app\model\paper;
use app\model\article;
use app\model\keywords;
use think\Controller;
use think\Db;
use think\process\pipes\Windows;

class Index extends Controller{
    
    public function index()
    {
        return $this->fetch('index');
    }

//返回关键词
public function keyword()
    {

        
        $data=keywords::column("keywords");
        
        
        //模糊查询
        
        //$data=article::where('title like "%qr%"')->select();

        //查询列
        //$data=article::column('title');
        return json($data);


    }
    
//查找功能返回数据
public function search()
{
    
    $content=input('searchContent');
    $data=paper::where('title like "%'.$content.'%"')->select();
    $this->assign('income', $data);
    return $this->fetch('informshow');

}
//点击关键词返回数据
public function searchkey($keyword)
{
    $data=paper::where('keywords like "%'.$keyword.'%"')->select();
    $this->assign('income', $data);
    return $this->fetch('informshow');

}
//返回关键词和词数
public function keywordandnum()
{
    $data=keywords::select();
    
    //$paper=M("paper");
    //$paper->where('type=1 AND status=1')->select();
    //$where['name']=array('like','jb51%');
    //$userForm->where($where)->select();
    //$data =paper::where("title","DeepPhys: Video-Based Physiological Measurement Using Convolutional Attention Networks")->select();
    //return json($data);
    
    //模糊查询
    
    //$data=article::where('title like "%qr%"')->select();

    //查询列
    //$data=article::column('title');
    $this->assign('income', $data);
    return $this->fetch('statistic');

}



    





    

        
        
    
}