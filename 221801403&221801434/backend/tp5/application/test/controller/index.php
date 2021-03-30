<?php
namespace app\test\controller;
use app\model\users;
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
 //注册接口
 public function rigister(){
    $username = isset($_POST['username']) ? htmlspecialchars($_POST['username']) : '';
    $password = isset($_POST['password']) ? htmlspecialchars($_POST['password']) : '';
    $data=users::where("username",$username)->find();
    if(!$data==null)
    {
        return "用户名已存在";
    }
    else{
        $da=[
            'username'=>$username,
            'password'=>$password
        ];
        users::insert($da);
        return "注册成功";
    }
}
//登陆接口
public function login($username,$password)
{   
    $data=users::where("username",$username)->where("password",$password)->find();
    if(!$data==null)
    {
       return $this->fetch("index");
        
    }
    else{
        return "用户名或密码错误";
    }
}

//删除论文

public function dele()
{
    $link = isset($_POST['link']) ? htmlspecialchars($_POST['link']) : '';
    //$url=isset($_POST['url']) ? htmlspecialchars($_POST['url']) : '';
    paper::where('link',$link)->delete();
    //return $data;
    return '删除成功';

}
//增加论文
public function add()
{
    $title = isset($_POST['title']) ? htmlspecialchars($_POST['title']) : '';
    $abstract = isset($_POST['abstract']) ? htmlspecialchars($_POST['abstract']) : '';
    $typeandyear = isset($_POST['typeandyear']) ? htmlspecialchars($_POST['typeandyear']) : '';
    $keywords = isset($_POST['keywords']) ? htmlspecialchars($_POST['keywords']) : '';
    $releasetime = isset($_POST['releasetime']) ? htmlspecialchars($_POST['releasetime']) : '';
    $link = isset($_POST['link']) ? htmlspecialchars($_POST['link']) : '';
    $da=[
        'title'=>$title,
        'abstract'=>$abstract,
        'typeandyear'=>$typeandyear,
        'keywords'=>$keywords,
        'releasetime'=>$releasetime,
        'link'=>$link,

    ];
    paper::insert($da);
    return "添加成功";
}
    
public function see()
    {
        
        return $this->fetch('login');
    }

    public function seesta()
    {
        
        return $this->fetch('statistic');
        
    }




    

        
        
    
}