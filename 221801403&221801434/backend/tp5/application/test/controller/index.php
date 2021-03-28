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



    





    

        
        
    
}