.class final Lcom/vk/stories/editor/multi/CameraReplyDelegate$bind$8;
.super Lkotlin/jvm/internal/Lambda;
.source "CameraReplyDelegate.kt"

# interfaces
.implements Lkotlin/jvm/b/Functions2;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/vk/stories/editor/multi/CameraReplyDelegate;->a(Lcom/vk/cameraui/entities/StoryRawData3;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x18
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lkotlin/jvm/internal/Lambda;",
        "Lkotlin/jvm/b/Functions2<",
        "Landroid/graphics/Bitmap;",
        "Lkotlin/Unit;",
        ">;"
    }
.end annotation


# instance fields
.field final synthetic $sticker:Lcom/vk/attachpicker/stickers/reply/ReplySticker;


# direct methods
.method constructor <init>(Lcom/vk/attachpicker/stickers/reply/ReplySticker;)V
    .locals 0

    iput-object p1, p0, Lcom/vk/stories/editor/multi/CameraReplyDelegate$bind$8;->$sticker:Lcom/vk/attachpicker/stickers/reply/ReplySticker;

    const/4 p1, 0x1

    invoke-direct {p0, p1}, Lkotlin/jvm/internal/Lambda;-><init>(I)V

    return-void
.end method


# virtual methods
.method public final a(Landroid/graphics/Bitmap;)V
    .locals 1

    .line 1
    iget-object v0, p0, Lcom/vk/stories/editor/multi/CameraReplyDelegate$bind$8;->$sticker:Lcom/vk/attachpicker/stickers/reply/ReplySticker;

    check-cast v0, Lcom/vk/attachpicker/stickers/reply/ReplyVideoViewSticker;

    invoke-virtual {v0, p1}, Lcom/vk/attachpicker/stickers/reply/ReplyVideoViewSticker;->setPreviewBitmap(Landroid/graphics/Bitmap;)V

    return-void
.end method

.method public bridge synthetic invoke(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    .line 1
    check-cast p1, Landroid/graphics/Bitmap;

    invoke-virtual {p0, p1}, Lcom/vk/stories/editor/multi/CameraReplyDelegate$bind$8;->a(Landroid/graphics/Bitmap;)V

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p1
.end method