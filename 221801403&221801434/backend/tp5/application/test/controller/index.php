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



    





    

        
        
    
}